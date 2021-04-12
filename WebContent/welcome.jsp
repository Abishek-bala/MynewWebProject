<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>welcome page</title>
</head>
<body>
	<h1>Welcome...........To our Shopping Site....<%=session.getAttribute("userid") %></h1>
	<%@ include file="logout.jsp" %>
	<hr>
	<h1>Go Shopping....</h1>
		<form action="shopping.do" method="post">
			<input type="hidden" name="formid" value="goshop">
			<input type="submit" value="Shopping........">
		</form>
</body>
</html>