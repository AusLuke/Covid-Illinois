<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<h3 align="center"> Highest Infection Rate By Population, Aggregated By State Instead of County </h3>
	<table border="1" width="70%" align="center">
	<tr>
		<th>State</th>
		<th>Number of Cases</th>
		<th>Number of Deaths</th>
		<th>Population</th>
		<th>Infection Rate</th>
	</tr>
<c:forEach items="${CQ4List}" var="user">
	<tr>
		<td align="center">${user.state }</td>
		<td align="right">${user.casesS }</td>
		<td align="right">${user.deathsS }</td>
		<td align="right">${user.populationS }</td>
		<td align="center">${user.infectionRate }</td>
	</tr>
</c:forEach>
</table>
</body>
</html>