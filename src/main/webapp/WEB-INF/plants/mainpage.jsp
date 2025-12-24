<%--
  Created by IntelliJ IDEA.
  User: lenus
  Date: 31.05.2025
  Time: 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Редкие растения</title>
    <style>
        body {
            font-family: Arial;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 8px;
            border: 1px solid #ddd;
            text-align: left;
        }
        .actions {
            white-space: nowrap;
        }
        .logout {
            float: right;
        }
    </style>
</head>
<body>

<a href="${pageContext.request.contextPath}/logout" class="logout">Выйти</a>

<h2>Мои растения</h2>

<a href="${pageContext.request.contextPath}/plants/create">+ Добавить</a>

<form method="get" action="${pageContext.request.contextPath}/plants" style="margin: 15px 0;">
    <input type="text" name="name" placeholder="Поиск по имени" value="${param.name}">
    <input type="submit" value="Найти">
</form>

<c:if test="${not empty requestScope.message}">
    <p style="color: red;"><c:out value="${requestScope.message}"/></p>
</c:if>

<table>
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Оценка редкости</th>
        <th>Действия</th>
    </tr>
    <c:forEach var="plant" items="${requestScope.plants}">
    <tr>
        <td><c:out value="${plant.id}"/></td>
        <td><c:out value="${plant.name}"/></td>
        <td><c:out value="${plant.rarityScore}"/></td>
        <td class="actions">
            <a href="${pageContext.request.contextPath}/plants/edit?id=${plant.id}">Редактировать</a>

            <form action="${pageContext.request.contextPath}/plants/delete" method="post" style="display:inline;">
                <input type="hidden" name="id" value="${plant.id}">
                <input type="submit" value="Удалить">
            </form>
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>
