<%--
  Created by IntelliJ IDEA.
  User: lenus
  Date: 31.05.2025
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Ошибка<c:out value="${requestScope.code}"/></title>
    <style>
        body {
            font-family: Arial;
            text-align: center;
            margin-top: 50px;
        }
        h1 {
            color: red;
            font-size: 24px;
        }
        a {
            color: blue;
        }
    </style>

</head>
<body>
<h1>Ошибка <c:out value="${requestScope.code}"/>: <c:out value="${requestScope.message}"/></h1>
<p><a href="${pageContext.request.contextPath}/plants">Назад</a></p>

</body>
</html>
