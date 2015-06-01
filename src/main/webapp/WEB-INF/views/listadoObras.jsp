<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type = "text/css"> 
		<%@include file = "/resources/css/menustyle.css"%>  
	</style> 

<title>Listado de obras</title>
</head>
<h1>Gestion de contenidos</h1>
<body>
<p>
 <FORM NAME="form1" action="showNuevaObra.html" METHOD="GET">
           				<input type="image" src="resources/images/new.png">
    					</FORM>

</p>
		<div id="space">
			<table id="listado">
			<th>Obra</th>
   <th>Autor</th>

				<c:forEach items="${obras}" var="obra">
					<tr class="alt">
						<td class="otro">
						<c:out value="${obra.titulo}" />  
						</td>
						<td class="otro">
						<c:out value="${obra.autor.nombres}" />  
						</td>
						<div position:relative >
						
						<td class="but">
						 <FORM NAME="form1" action="consultarObra.html?idObra=${obra.id}" METHOD="POST">
           				<input type="image" width="20%" height="20%"src="resources/images/consultar.png">
    					</FORM>
						</td>
						
						<td class="but">
							 <FORM NAME="form2" action="showEditarObra.html?idObra=${obra.id}" METHOD=POST>
						 <input type="image" width="20%" height="20%"src="resources/images/edit.png">
    					</FORM>
						</td>
    					
    					<td class="but">
							 <FORM NAME="form3" action="borrarObra.html?idObra=${obra.id}" METHOD=POST>
						 <input type="image" width="20%" height="20%" src="resources/images/delete.png">
    					</FORM>
						</td>
    				
						
						</div>
						
					</tr>
					</c:forEach>
			</table>

		</div>
		

</body>
</html>