<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<h3 align="center"> List County in State with Highest Infected Death Rate </h3>
	<table border="1" width="70%" align="center">
	<tr>
		<th>State</th>
		<th>County</th>
		<th>Cases</th>
		<th>Deaths</th>
		<th>Death Rate</th>
	</tr>
<c:forEach items="${CQ2List}" var="user">
	<tr>
		<td align="center">${user.state }</td>
		<td align="center">${user.county }</td>
		<td align="right">${user.casesS }</td>
		<td align="right">${user.deathsS }</td>
		<td align="center">${user.deathRate }</td>
	</tr>
</c:forEach>
</table>
</body>
</html>