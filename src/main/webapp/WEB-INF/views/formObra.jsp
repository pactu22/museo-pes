<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type = "text/css"> 
		<%@include file = "/resources/css/menustyle.css"%>  
	</style> 
		<script type="text/javascript">
		function validate(){
 			if (document.nuevaobra.titulo.value == "" | 
 					document.nuevaobra.titulo.beacon == "" |
 					document.nuevaobra.autor.value == "" |
 					document.nuevaobra.info.value == "" |
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
<body >
<div  >
	<h1>Nueva Obra</h1>
		<form name="nuevaobra" class ="idform" action="nuevaObra" onsubmit="return validate();" method="post" id="obraForm">
				<p><label>Titulo</label>
					<input type="text" name="titulo" placeholder="Introduzca titulo"  >
				</p>
					
				<p><label>Beacon</label>
				
					<input type="text" name="beacon" placeholder="Introduzca beacon"  ></p>
				<p><label>Autor</label>
				
					<input list="autor" name="autor" value="Selecciona un autor" >
					 <datalist id="autor">
					 <c:forEach items="${autores}" var="autor">
               		 	<option value="${autor.nombres}" value="hola" >  ${autor.id} 
               		 	
					</c:forEach>
               		 </datalist>
				</p>
				
				<p><label>Informacion</label>
					<input type="text" name="info" placeholder="Introduzca informacion"  >
				</p>
				
				<p><label>Estilo</label>
					<input type="text" name="estilo" placeholder="Introduzca estilo"  >
				</p>
				
				<p><label>Coleccion</label>
					<input list="coleccion" name="coleccion" value="Ninguna" >
					 <datalist id="coleccion">
					 <c:forEach items="${colecciones}" var="col">
               		 	<option value="${col}">
					</c:forEach>
               		 </datalist>
				</p>
				<p> <input type="submit" value="Crear obra" id="crear" /></p>
		</form>
	</div>
</body>
</html>