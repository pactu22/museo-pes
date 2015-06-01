<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><style type = "text/css"> 
		<%@include file = "/resources/css/menustyle.css"%>  
	</style> 
		<script type="text/javascript">
			function validate(){
	 			if (document.nuevaobra.titulo.value == "" || 
	 					document.nuevaobra.titulo.value == "" ||
	 					document.nuevaobra.autor.value == "" ||
	 					document.nuevaobra.info.value == "" ||
	 					document.nuevaobra.estilo.value == ""
	 					
	 			) {
	 				alert("Faltan campos a rellenar");
					return false;
				}
				else{
					return true;
				}
			}
		</script>
<title>Insert title here</title>
</head>
<body>
<div>
	<h1>Editar Obra</h1>
	
		<form:form action="editarObra" modelAttribute="obra">
				<p><label>Titulo</label>
					<form:input path="titulo"/>
				</p>					
				<p><label>Beacon</label>
					<form:input path="beacon"/>
				</p>
				<p><label>Autor</label>
					<form:select path="autorId" items="${autores}" itemValue="id" itemLabel="nombres" />
				</p>
				
				<p><label>Informacion</label>
					<form:input path="informacion"/>
				</p>
				
				<p><label>Estilo</label>
					<form:input path="estilo"/>
				</p>
				
				<p><label>Coleccion</label>
					 <form:select path="coleccion" >
					 <form:options items="${colecciones}" />
					   </form:select>
				</p>
				<form:hidden path="id"/>
				<p> <input type="submit" value="Editar obra" id="editar" /></p>
		</form:form>
	</div>
</body>
</html>