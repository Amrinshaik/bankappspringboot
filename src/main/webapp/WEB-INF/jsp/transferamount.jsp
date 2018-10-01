<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="home.jsp" %>
<title>Transfer Money</title>

</head>
<body bgcolor="cyan">
<br><br>
<h1>transaction...</h1>
<form:form action="transferAmount" method="post" modelAttribute="customer">
	<br><br>
	<form:label path="fromAcc"> From Account: </form:label>
		<form:input type="text" value="${customer.account.accountId}" path="fromAcc" readonly="readonly"/>
		<br> <br>
	<form:label path="toAcc"> To Account: </form:label>
		<form:input type="text" path="toAcc" required="required"/>
		<br> <br>
	<form:label path="amount"> Amount: </form:label>
		<form:input type="number" path="amount" min="1000" max="50000" required="required"/>
		<br><br>
		<input type="reset" value="Clear"/>
	<input type="submit" value="Submit"/>
	<br><br>
	</form:form>
</body>
</html>