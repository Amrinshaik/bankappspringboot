<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%@ include file="loginheader.jsp" %>
<body bgcolor="cyan">
<form:form action="editProfile" method="post" modelAttribute="customer">
<form:label path="customerId">Customer Id: </form:label>
<form:input type="text" value="${customer.customerId}" path="customerId" readonly="readonly"/>
<br><br>
<form:label path="customerName">Customer Name: </form:label>
<form:input type="text" path="customerName" size="15" required="required"/>
<br><br>
<form:label path="email">Email </form:label>
<form:input type="email" path="email" size="15" required="required"/>
<br><br>
<form:label path="password">Password</form:label>
<form:input type="password" path="password" placeholder="...." readonly="readonly"/>
<br><br>
<form:label path="address">Address </form:label>
<form:input type="text" path="address" required="required"/>

<form:label path="dateOfBirth">Date Of Birth </form:label>
<form:input type="date" path="dateOfBirth" required="required"/>
<input type="submit" value="Update"/>
<br><br>
</form:form>
</body>
</html>