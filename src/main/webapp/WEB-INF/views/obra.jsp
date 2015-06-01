<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type = "text/css"> 
		<%@include file = "/resources/css/menustyle.css"%>  
	</style> 
<title>Ver obra</title>
</head>
<h1>Consultar obra</h1>
<body>
<div id="space" class ="idObra">

       		<p> <label>Título:</label>
				${obra.titulo}
			</p>
          <p>  <label>Autor:</label>
           ${obra.autor.nombres}</p>
          <p>  <label>Beacon:</label>
          ${obra.beacon}</p>
          <p>  <label>Información:</label>
          ${obra.informacion}</p>
          <p>  <label>Estilo:</label>
           ${obra.estilo}</p>
          <p>  <label>Colección:</label>
          ${obra.coleccion}</p>
          <p>   <label>Imagen:</label>
          supuesta imagen</p>
          <p>  <label>Audio:</label>
         supuesto audio</p>
          <p>  <label>Vídeo:</label>
           supuesto video</p>

	   </div>
</body>
</html>