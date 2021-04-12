<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="/WEB-INF/myjsptags.tld" prefix="coda" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Invoice</title>
</head>
<body>
	<h1>Invoice page</h1>
	<table>
		<tr>
			<th>Item name</th>
			<th>Quantity</th>
			<th>Item Price</th>
		</tr>
			
	<coda:invoice session="<%=session%>"/>
	<hr/>
	
	</table>
	<form action="createFiles.do" method="post">
		<input type="hidden" name="formid" value="generateInvoice"/>
		<input type="submit" value="get Invoices"/>
	</form>
	</body>
</html>