<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<h3 align="center"> County Covid </h3>
	<table border="1" width="70%" align="center">
	<tr>
		<th>date</th>
		<th>county</th>
		<th>state</th>
		<th>fips</th>
		<th>cases</th>
		<th>deaths</th>
	</tr>
<c:forEach items="${E1List}" var="user">
	<tr>
		<td>${user.date }</td>
		<td>${user.county }</td>
		<td>${user.state }</td>
		<td>${user.fips }</td>
		<td>${user.cases }</td>
		<td>${user.deaths }</td>
	</tr>
</c:forEach>
</table>
</body>
</html>