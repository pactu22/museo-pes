<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listado de obras</title>
</head>
<h1>Gestion de contenidos</h1>
<body>
		 <FORM NAME="form1" METHOD="GET">
         <INPUT TYPE="BUTTON" VALUE="Nueva Obra" onclick="location.href='/pes/showNuevaObra'">
    	</FORM>

		<div id="space">
			<table class="table table-bordered table-hover table-striped">
			<th>Obra</th>
   <th>Autor</th>

				<c:forEach items="${obras}" var="obra">
					<tr>
						<td>
						<c:out value="${obra.titulo}" />  
						</td>
												<td>
						<c:out value="${obra.autor.nombres}" />  
						</td>
						<td>
						 <FORM NAME="form1" METHOD="GET">
           				 <INPUT TYPE="BUTTON" VALUE="Consultar" onclick="location.href='/pes/consultarObra.html?idObra=${obra.id}'">
    					</FORM>
						</td>
						<td>
						 <FORM NAME="form2" METHOD=GET>
           				 <INPUT TYPE="BUTTON" VALUE="Editar" onclick="location.href='/pes/showEditarObra.html?idObra=${obra.id}'">
    					</FORM>
						</td>
						<td>
						 <FORM NAME="form3" METHOD="GET">
           				 <INPUT TYPE="BUTTON" VALUE="Borrar" onclick="location.href='/pes/borrarObra.html?idObra=${obra.id}'">
    					</FORM>
						</td>
					</tr>
					</c:forEach>
			</table>
		</div>
</body>
</html>