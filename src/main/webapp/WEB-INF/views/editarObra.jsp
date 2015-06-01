<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><style type = "text/css"> 
		<%@include file = "/resources/css/menustyle.css"%>  
	</style> 
		<script type="text/javascript">
			function validate(){
				var titulo = document.editarobra.titulo.value;
				var info = document.editarobra.informacion.value;
				var estilo = document.editarobra.estilo.value;
				var beacon = document.editarobra.beacon.value;
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
<title>Editar Obra</title>
</head>
<body>
<div>
	
	
	<c:choose>
      <c:when test="${edit ==true}"> true
      <h1>Editar Obra</h1>
      	<form:form action="editarObra" class ="idform"  modelAttribute="obra" enctype="multipart/form-data" name="editarobra" onsubmit="return validate()" >
				<p><label>Título: </label>
					<form:input path="titulo"/>
				</p>					
				<p><label>Beacon: </label>
					<form:input path="beacon" />
				</p>
				<p><label>Autor: </label>
					<form:select path="autorId" items="${autores}" itemValue="id" itemLabel="nombres" />
				</p>
				
				<p><label>Información: </label>
					<form:textarea  path="informacion"  rows="4" cols="30"/>
				</p>
				
				<p><label>Estilo: </label>
					<form:input path="estilo"/>
				</p>
				
				<p><label>Colección: </label>
					 <form:select path="coleccion" items="${colecciones}" />
				</p>
				
				<p><label>Multimedia 1</label>
					<form:input path="item1" type="file" />
				</p>
				<p><label>Multimedia 2</label>
					<form:input path="item2" type="file" />
				</p>
				<p><label>Multimedia 3</label>
					<form:input path="item3" type="file" />
				</p>
				
				<p> <input type="submit" value="Editar obra" id="editar" /></p>
		</form:form>
      </c:when>

      <c:otherwise> false
      <h1>Consultar Obra</h1> 
      	<form:form action="editarObra" class ="idform"  modelAttribute="obra" name="editarobra" onsubmit="return validate()" >
				<p><label>Título: </label>
					<form:input path="titulo" readonly="true"/>
				</p>					
				<p><label>Beacon: </label>
					<form:input path="beacon" readonly="true"/>
				</p>
				<p><label>Autor: </label>
					<form:input path="autor" readonly="true" />
				</p>
				
				<p><label>Información: </label>
					<form:textarea  path="informacion"  rows="4" cols="30" readonly="true"/>
				</p>
				
				<p><label>Estilo: </label>
					<form:input path="estilo" readonly="true"/>
				</p>
				
				<p><label>Colección: </label>
					 <form:input path="coleccion"  readonly="true" />
				</p>
				
		</form:form>
      </c:otherwise>
</c:choose>

	
	</div>
</body>
</html>