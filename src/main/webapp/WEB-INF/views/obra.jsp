<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ver obra</title>
</head>
<h1>Gestion de contenidos</h1>
<h2>Consultar obra</h2>
<body>
<div id="space">
       <table cellspacing="5" cellpadding="0" border="0" style="margin-top:5px">
         <tr>
       		 <td>Titulo:</td>
            <td>${obra.titulo}</td>
         </tr>
           <tr>
           <td>Autor:</td>
           <td>${obra.autor.nombres}</td>
         </tr>
         <tr>
           <td>Beacon:</td>
           <td>${obra.beacon}</td>
         </tr>
         <tr>
           <td>Informacion:</td>
           <td>${obra.informacion}</td>
         </tr>
       	<tr>
           <td>Estilo:</td>
           <td>${obra.estilo}</td>
         </tr>
         <tr>
           <td>Coleccion:</td>
           <td>${obra.coleccion}</td>
         </tr>
         <tr>
          <tr>
           <td>Imagen:</td>
           <td>supuesta imagen</td>
         </tr>
            <tr>
           <td>Audio</td>
           <td>supuesto audio</td>
         </tr>
             <tr>
           <td>Video</td>
           <td>supuesto video</td>
         </tr>
       </table>
	   </div>
</body>
</html>