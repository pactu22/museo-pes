<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="space">
		<form  name="obraEdit"  action="editarObra" method="post" id="obraEdit">
			<table cellspacing="5" cellpadding="0" border="0"
				style="margin-top: 5px">
				<tr>
					<td>Titulo:</td>
					<td><input type="text" name="titulo" value= "${obra.titulo}"> </td>
				</tr>
				<tr>
					<td>Autor:</td>
					<td><input type="text" name="autor" value = "${obra.autor.id}"></td>
				</tr>
				<tr>
					<td>Beacon:</td>
					<td><input type="text" name="beacon" value = "${obra.beacon}"></td>
				</tr>
				<tr>
					<td>Informacion:</td>
					<td><input type="text" name="info" value ="${obra.informacion}"></td>
				</tr>
				<tr>
					<td>Estilo:</td>
					<td><input type="text" name="estilo" value="${obra.estilo}"> </td>
				</tr>
				<tr>
					<td>Coleccion:</td>
					 <td>
						 <c:choose>
						      <c:when test="${empty obra.coleccion}">
						       <input list="coleccions" name="coleccion" value="Ninguna" >
						      </c:when>
						
						      <c:otherwise>
						       <input list="coleccions" name="coleccion" value="${obra.coleccion}" >
						      </c:otherwise>	
	</c:choose>
					
					 <datalist id="coleccions">
					 <c:forEach items="${colecciones}" var="col">
               		 	<option value="${col}">
					</c:forEach>
               		 </datalist>
            </td>
				</tr>

				<tr>
					<td><input type="submit" value="Editar obra" id="crear" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>