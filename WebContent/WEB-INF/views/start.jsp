<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Session</title>
<script type="text/javascript">
window.addEventListener("click",dofirst,false);
function dofirst() {
    var button = document.getElementById("submit");
	button.addEventListener("click",savecrap,false);
}
function savecrap()
{
	var two=document.getElementById("userName").value;
        var one="1";
        sessionStorage.setItem(one,two);
        
}

</script>


</head>
<body>
	<form:form action="end" commandName="loginForm" onsubmit="dofirst()">
	<table>
		<tr>
			<td>User Name:<FONT color="red"><form:errors path="userName" /></FONT></td>
		</tr>
		<tr>
			<td><form:input path="userName" /></td>
		</tr>
		<tr>
			<td><input type="submit" id="submit" value="Submit"/></td>
		</tr>
	</table>
	</form:form>
	</body>
</html>