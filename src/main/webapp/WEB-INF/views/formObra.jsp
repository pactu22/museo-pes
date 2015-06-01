<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type = "text/css"> 
		<%@include file = "/resources/css/menustyle.css"%>  
	</style> 
		<script type="text/javascript">
		function validate(){
			var titulo = document.nuevaobra.titulo.value;
			var info = document.nuevaobra.informacion.value;
			var estilo = document.nuevaobra.estilo.value;
			var beacon = document.nuevaobra.beacon.value;
			if (titulo==null | titulo=="" | info==null | info=="" | estilo==null | estilo=="" | beacon==null | beacon=="")							
 			{
 				alert("Faltan campos a rellenar");
				return false;
			}
			else{
				return true;
			}
		}
	</script>
<title>Nueva Obra</title>
</head>
<body >
<div  >
	<h1>Nueva Obra</h1>
	
		<form:form action="nuevaObra" class ="idform" modelAttribute="obra" name="nuevaobra" onsubmit="return validate()">
				<p><label>Título: </label>
					<form:input path="titulo" placeholder="Introduzca titulo"/>
				</p>
					
				<p><label>Beacon: </label>				
					<form:input path="beacon" placeholder="Introduzca beacon" />
				</p>
				
				<p><label>Autor: </label>
					<form:select path="autorId" items="${autores}" itemValue="id" itemLabel="nombres" />
				</p>
				
				<p><label>Información: </label>
					<form:textarea path="informacion" rows="4" cols="30" placeholder="Introduzca informacion" />
				</p>
				
				<p><label>Estilo: </label>
					<form:input path="estilo" placeholder="Introduzca estilo" />
				</p>
				
				<p><label>Colección: </label>
					 <form:select path="coleccion" items="${colecciones}" />
				</p>
				
				<p> <input type="submit" value="Crear obra" id="crear" /></p>
		</form:form>
	</div>
</body>
</html>