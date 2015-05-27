<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
		<form name="obraEdit" class ="idform" action="editarObra" onsubmit="return validate();" method="post" id="obraEdit">
				<p><label>Titulo</label>
					<input type="text" name="titulo"  value = "${obra.titulo}"  >
				</p>
					
				<p><label>Beacon</label>
				
					<input type="text" name="beacon" value = "${obra.beacon}"  ></p>
				<p><label>Autor</label>
				<input type = "hidden" name="autor" value = "${obra.autor.id}">
					
					<input list="autor" name="autor" value="${obra.autor.nombres}" >
					 <datalist id="autor">
					 <c:forEach items="${autores}" var="autor">
               		 	<option value="${autor.nombres}" >  ${autor.id} 
               		 	
					</c:forEach>
               		 </datalist>
				</p>
				
				<p><label>Informacion</label>
					<input type="text" name="info"  value ="${obra.informacion}" >
				</p>
				
				<p><label>Estilo</label>
					<input type="text" name="estilo"  value = "${obra.estilo}"  >
				</p>
				
				<p><label>Coleccion</label>
					 <c:choose>
						      <c:when test="${empty obra.coleccion}">
						       <input list="colecciones" name="coleccion" value="Ninguna" >
						      </c:when>
						
						      <c:otherwise>
						       <input list="colecciones" name="coleccion" value="${obra.coleccion}" >
						      </c:otherwise>	
						</c:choose>
					 <datalist id="colecciones">
					 <c:forEach items="${colecciones}" var="col">
               		 	<option value="${col}">
					</c:forEach>
               		 </datalist>
				</p>
				<p> <input type="submit" value="Editar obra" id="editar" /></p>
		</form>
	</div>
</body>
</html>