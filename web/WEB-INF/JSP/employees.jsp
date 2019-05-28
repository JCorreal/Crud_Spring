<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Crud_Spring</title>
</head>
<body>
	<h3>Manejar Empleados</h3>
        

        
	<form:form method="post" action="/SpringMVCTutorial/employee.html" commandName="employee">
	    <div>
		<table style="width: 300px">
                        <tr>
				<td>Id *</td>
				<td><form:input type="text" path="empleado_id" /></td>
			</tr>
			<tr>
				<td>Nombres *</td>
				<td><form:input type="text" path="nombres" /></td>
			</tr>
			<tr>
				<td>Apellidos *</td>
				<td><form:input type="text" path="apellidos" /></td>
			</tr>
			<tr>
				<td>Telefono *</td>
				<td><form:input type="text" path="telefono" /></td>
			</tr>
			<tr>
				<td>Correo *</td>
				<td><form:input type="text" path="correo" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Enviar" /></td>
			</tr>
		</table>
	    </div>
	</form:form>
	<br>
	<br>
	<h3>Lista de Empleados</h3>
        <table border="1">
		<tr>                    
			<th>Nombres</th>
			<th>Apellidos</th>
			<th>Telefono</th>
			<th>Correo</th>
			<th>Accion</th>
		</tr>
                
		<c:forEach items="${employeeList}" var="emp">
			<tr>
				<td width="60" align="center">${emp.nombres}</td>			
				<td width="60" align="center">${emp.apellidos}</td>
				<td width="60" align="center">${emp.telefono}</td>
				<td width="60" align="center">${emp.correo}</td>
				<td width="60" align="center"><a href="edit/${emp.empleado_id}">Actualizar</a>/<a href="delete/${emp.empleado_id}">Eliminar</a></td>                                
			</tr>
		</c:forEach>
	</table>
</body>
</html>