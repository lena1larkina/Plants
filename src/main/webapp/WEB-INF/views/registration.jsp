<%--
  Created by IntelliJ IDEA.
  User: lenus
  Date: 31.05.2025
  Time: 0:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Регистрация</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
        }
        .container {
            background: white;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        h2 {
            color: #333;
            text-align: center;
            margin-top: 0;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }

        input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 10px;
            background: #4267B2;
            color: white;
            border: none;
            border-radius: 4px;
            font-weight: bold;
            cursor: pointer;
            margin-top: 10px;
        }

        .error {
            color: #d9534f;
            margin-top: 15px;
            text-align: center;
        }

        .password-container {
            display: flex;
            align-items: center;
        }

        .toggle-password {
            margin-left: 10px;
            cursor: pointer;
            color: #666;
            font-size: 14px;
        }
    </style>
    <script>
        function togglePassword() {
            const field = document.getElementById("passwordField");
            field.type = field.type === "password" ? "text" : "password";
        }
    </script>

</head>
<body>
<div class="container">
    <h2>Регистрация</h2>
    <form method="post" action="<%= request.getContextPath() + "/registration" %>">
        <div class="form-group">
            <label>Логин</label>
            <input type="text" name="login" autocomplete="off" minlength="4" required>
        </div>
        <div class="form-group">
            <label>Пароль</label>
            <div class="password-container">
                <input type="password" name="password" autocomplete="off" id="passwordField" minlength="6" required>
                <span class="toggle-password" onclick="togglePassword()">Показать</span>
            </div>
        </div>
        <button type="submit">Зарегистрироваться</button>
    </form>

    <c:if test="${not empty requestScope.message}">
        <div class="error">
            <c:out value="${requestScope.message}" />
        </div>
    </c:if>
</div>

</body>
</html>
