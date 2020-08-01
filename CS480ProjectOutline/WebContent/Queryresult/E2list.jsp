<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<h3 align="center"> County Information </h3>
	<table border="1" width="70%" align="center">
	<tr>
		<th>County</th>
		<th>State</th>
		<th>FIPS Number</th>
		<th>County Number</th>
		<th>Population</th>
	</tr>
<c:forEach items="${E2List}" var="user">
	<tr>
	    <td align="center">${user.county }</td>
		<td align="center">${user.state }</td>
		<td align="center">${user.fips }</td>
		<td align="center">${user.countyNum }</td>
		<td align="right">${user.populationS }</td>
	</tr>
</c:forEach>
</table>
</body>
</html>