
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/WEB-INF/myjsptags.tld" prefix="coda" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>User Login page</h1><br>
		<form action="login.do" method="post">
			<coda:internationalizedLogin session="<%=session%>"/>
			<input type="hidden" name = "formid" value = "LOGIN"/>
			<input type="Submit" value="Login"/>
		</form>
</body>
</html>