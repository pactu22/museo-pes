<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>New User Registration</title>
</head>
<body>

	<form:form action="newObra" modelAttribute="obra">
		<h1>New Obra</h1>
		<div>
			Username:
			<form:input path="titulo" />
		</div>
		<div>
			Autores:
			
			<input list="autor" name="autor" value="Selecciona un autor" >
					 <datalist id="autor">
               		 	<option value="Pepe" value="hola" > 
               		 	<option value="Juan" value="hola" >
               		 	<option value="MAria" value="hola" >
               		 	
               		 </datalist>
		</div>

		<div>
			Beacon:
			<form:input path="beacon" />
		</div>
		<div>
			Informacion
			<form:input path="informacion" />
		</div>
		<div>
			Estilo
			<form:input path="estilo" />
		</div>
		<div>
			Coleccion
			<form:input path="coleccion" />
		</div>

		<div>
			<input type="submit" value="Register" />
		</div>
No special
</form:form>
</body>
</html>
