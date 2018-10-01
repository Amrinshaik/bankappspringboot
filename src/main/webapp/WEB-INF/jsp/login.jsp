<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<%@ include file="header.jsp" %>
</head>
<body bgcolor="cyan" style="text-align:center" >
	<h1>Login to your account</h1>
	<hr>
<form:form action="login" method="post" modelAttribute="customer">
	<br><br><br>
		<form:label path="email"> Email: </form:label> 
		<form:input type="email" path="email" placeholder="enter your Email ID" required="required" /> 
		<br> <br>
		<form:label path="password"> Password: </form:label> 
		<form:input type="password" path="password" size="10" placeholder="enter password" required="required" /> 
		<br> <br>
	<input type="submit" value="Login"/>
		<br> <br>
</form:form>
</body>
</html>