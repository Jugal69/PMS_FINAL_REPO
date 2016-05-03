<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Role</title>
</head>
<body>
	<form:form action="roleAssigned"  commandName="assignRole">
	<table>
		<tr>
			<td>Function Name:<FONT color="red"><form:errors path="functionName" /></FONT></td>
		</tr>
		<tr>
			<td><form:input path="functionName" /></td>
		</tr>
		<tr>
			<td>Role ID:<FONT color="red"><form:errors
				path="roleId" /></FONT></td>
		</tr>
		<tr>
			<td><form:input path="roleId" /></td>
		</tr>
		<tr>
			<td>Function URL:</td>
			<td><form:input path="functionURL"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</form:form>
</body>
</html>