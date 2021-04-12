<%@page import="java.util.List"%>
<%@page import="Item.ItemDAOImpl"%>
<%@page import="Item.ItemDAO"%>
<%@page import="Item.ItemDTO"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/WEB-INF/myjsptags.tld" prefix="coda" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping</title>
</head>
<body>
	<h1>Vegetable Shop</h1>
	<form action="shop.do" method="post">
	<input type="hidden" name="formid" value="shop">
	<input type="hidden" name="shopid" value="shop1">
		<coda:listItems session="<%=session%>"/>
	
		<input type="submit" value="BuyItems">

	</form>
</body>
</html>