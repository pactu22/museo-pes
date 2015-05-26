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
		<form  name="obraForm"  action="nuevaObra" method="post" id="obraForm">
			<table cellspacing="5" cellpadding="0" border="0"
				style="margin-top: 5px">
				<tr>
					<td>Titulo:</td>
					<td><input type="text" name="titulo"></td>
				</tr>
				<tr>
					<td>Beacon:</td>
					<td><input type="text" name="beacon"></td>
				</tr>

				<tr>
					<td>Autor:</td>
					<td><input list="autor" name="autor" value="Selecciona un autor" >
					 <datalist id="autor">
					 <c:forEach items="${autores}" var="autor">
               		 	<option value="${autor.nombres} ${autor.id}">
					</c:forEach>
               		 </datalist>
				</tr>
				<tr>
					<td>Informacion:</td>
					<td><input type="text" name="info"></td>
				</tr>
				<tr>
					<td>Estilo:</td>
					<td><input type="text" name="estilo"></td>
				</tr>
				<tr>
					<td>Coleccion:</td>
					 <td><input list="coleccion" name="coleccion" value="Ninguna" >
					 <datalist id="coleccion">
					 <c:forEach items="${colecciones}" var="col">
               		 	<option value="${col}">
					</c:forEach>
               		 </datalist>
            </td>
				</tr>

				<tr>
					<td><input type="submit" value="Crear obra" id="crear" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>