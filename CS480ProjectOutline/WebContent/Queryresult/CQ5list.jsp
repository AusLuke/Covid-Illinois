<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<h3 align="center"> Cases In Each County As a Percentage of the Total Cases In the Entire State </h3>
	<table border="1" width="70%" align="center">
	<tr>
		<th>State</th>
		<th>County</th>
		<th>County Cases</th>
		<th>State Cases</th>
		<th>Infection Rate</th>
		<th>County Deaths</th>
		<th>Death Rate</th>
	</tr>
<c:forEach items="${CQ5List}" var="user">
	<tr>
		<td align="center">${user.state }</td>
		<td align="center">${user.county }</td>
		<td align="right">${user.casesS }</td>
		<td align="right">${user.casesS2 }</td>
		<td align="center">${user.infectionRate }</td>
		<td align="right">${user.deathsS }</td>
		<td align="center">${user.deathRate }</td>
	</tr>
</c:forEach>
</table>
</body>
</html>