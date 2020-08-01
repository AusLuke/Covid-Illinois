<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>County Covid</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <h1>Create, Read, Update, and Delete</h1>


<p style="color: red; font-weight: 900"> ${msg}</p>
<form action="<c:url value='/CRUDServlet'/>" method="post">
	<input type="hidden" name="Create" value="Create"/>
		Date：	<input type="text" name="date" /><br/>
		County:	<input type="text" name="county" /><br/>
		State:	<input type="text" name="state" /><br/>
		FIPS：	<input type="text" name="fips" /><br/>
		CountyNum: <input type="text" name="CountyNum" /><br/>
		Cases:	<input type="text" name="cases" /><br/>
		Deaths:	<input type="text" name="deaths" /><br/>
	<input type="submit" value="Create"/>
</form>
<form action="<c:url value='/CRUDServlet'/>" method="post">
	<input type="submit" name="Read" value="Read"/>
</form>
<form action="<c:url value='/CRUDServlet'/>" method="post">
	<input type="hidden" name="Update" value="Update"/>
		Date：	<input type="text" name="date" /><br/>
		County:	<input type="text" name="county" /><br/>
		State:	<input type="text" name="state" /><br/>
		FIPS：	<input type="text" name="fips" /><br/>
		CountyNum: <input type="text" name="CountyNum" /><br/>
		Cases:	<input type="text" name="cases" /><br/>
		Deaths:	<input type="text" name="deaths" /><br/>
	<input type="submit" value="Update"/>
</form>
<form action="<c:url value='/CRUDServlet'/>" method="post">
	<input type="hidden" name="Delete" value="Delete"/>
		Date：	<input type="text" name="date" /><br/>
		FIPS：	<input type="text" name="fips" /><br/>
		CountyNum: <input type="text" name="CountyNum" /><br/>
	<input type="submit" value="Delete"/>
</form>
  </body>
</html>
