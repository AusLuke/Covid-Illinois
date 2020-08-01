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
		<th>Date</th>
		<th>County</th>
		<th>State</th>
		<th>FIPS</th>
		<th>County Number</th>
		<th>Cases</th>
		<th>Deaths</th>
	</tr>
<c:forEach items="${E1List}" var="user">
	<tr>
		<td align="center">${user.date }</td>
		<td align="center">${user.county }</td>
		<td align="center">${user.state }</td>
		<td align="center">${user.fips }</td>
		<td align="center">${user.countyNum }</td>
		<td align="right">${user.casesS }</td>
		<td align="right">${user.deathsS }</td>
	</tr>
</c:forEach>
</table>
</body>
</html>