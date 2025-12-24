<%--
  Created by IntelliJ IDEA.
  User: lenus
  Date: 31.05.2025
  Time: 0:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>
        <c:if test="${requestScope.plant != null}">Редактирование </c:if>
        <c:if test="${requestScope.plant == null}">Создание </c:if>растения
    </title>
    <style>
        body {
            font-family: Arial;
            padding: 20px;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input {
            margin-top: 5px;
            padding: 5px;
            width: 200px;
        }
        button {
            margin-top: 15px;
            padding: 5px 10px;
        }
    </style>
</head>
<body>
<h2>
    <c:if test="${requestScope.plant != null}">Редактировать </c:if>
    <c:if test="${requestScope.plant == null}">Создать </c:if>растение
</h2>
<form method="post" action="${pageContext.request.contextPath}/plants<c:if test="${requestScope.plant != null}">/edit</c:if><c:if test="${requestScope.plant == null}">/create</c:if>">

    <c:if test="${requestScope.plant != null}">
    <input type="hidden" name="id" value="${requestScope.plant.id}" />
    </c:if>

        <div>
            <label for="name">Имя:</label>
            <input type="text" id="name" name="name"
                   value="${requestScope.plant.name}" required />

            <label for="score">Оценка редкости:</label>
            <input type="number" id="score" name="score" min="1"
                   value="${requestScope.plant.rarityScore}" required />
        </div>
    <div>
        <button type="submit">Сохранить</button>
    </div>
</form>
    <p><a href="${pageContext.request.contextPath}/plants">Назад к списку</a></p>

    <c:if test="${not empty requestScope.message}">
        <p style="color: red;"><c:out value="${requestScope.message}"/></p>
    </c:if>

</body>
</html>
