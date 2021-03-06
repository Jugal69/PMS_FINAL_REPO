
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- This JSP displays the List of the Users from UserDetails Table (For Admin)

 @author Adarsh
 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Users</title>
</head>
<body>
	<c:if test="${!empty users}">

		<table align="left" border="1">
			<tr>
				<th>User Name</th>
				<th>User Role</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.userName}" /></td>
					<td><c:choose>
							<c:when test="${user.roleId=='1'}">
								<c:out value="Student" />
							</c:when>
							<c:when test="${user.roleId=='2'}">
								<c:out value="Faculty" />
							</c:when>
							<c:when test="${user.roleId=='3'}">
								<c:out value="Student-TPC" />
							</c:when>
							<c:when test="${user.roleId=='4'}">
								<c:out value="Faculty-TPC" />
							</c:when>
							<c:when test="${user.roleId=='5'}">
								<c:out value="TPO" />
							</c:when>
							<c:otherwise>
								<c:out value="Admin" />
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<tr>
		<td><a href="AdminHome">Home</a></td>
	</tr>

</body>

</html>
