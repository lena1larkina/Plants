<%--
  Created by IntelliJ IDEA.
  User: lenus
  Date: 30.05.2025
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Вход в систему</title>
</head>
<body>
<h2>Вход в систему</h2>
<form method="post" action="<%= request.getContextPath() + "/login" %>">
    <p>
        <label>Логин</label><br/>
        <input type="text" name="login">
    </p>
    <p>
        <label>Пароль</label><br/>
        <input type="password" name="password">
    </p>
    <button type="submit">Войти</button>
</form>

<c:if test="${not empty requestScope.message}">
    <div class="error">
        <c:out value="${requestScope.message}" />
    </div>
</c:if>

<form action="<%= request.getContextPath() + "/registration" %>" method="get">
    <button type="submit">Зарегистрироваться</button>
</form>

</body>
</html>
