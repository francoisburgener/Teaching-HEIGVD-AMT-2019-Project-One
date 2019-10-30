<%--
  Created by IntelliJ IDEA.
  User: Mon pc
  Date: 21.10.2019
  Time: 09:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>JSP page country</title>
</head>
<body>
<h1>Countries</h1>
<table>
    <c:forEach items="${countries}" var="country">
        <tr>
            <td>${country.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>