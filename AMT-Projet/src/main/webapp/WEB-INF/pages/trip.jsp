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
    <title>JSP page trip</title>
</head>
<body>
<h1>Trips of ${username}</h1>
<table>
    <c:forEach items="${trips}" var="trip">
        <tr>
            <td>${trip.idTrip}</td>
            <td>${trip.idUser}</td>
            <td>${trip.idCountry}</td>
            <td>${trip.visited}</td>
            <td>${trip.date}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>