<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="../css/my_style.css">

    <title>Текстовый квест</title>
</head>
<body>
<div class="container">
    <h1 class="mt-5 text-center">Текстовый квест</h1>
    <p class="lead">Привет, <c:out value="${sessionScope.playerName}"/>!</p>
    <p><c:out value="${requestScope.questionText}" /></p>
    <form action="game" method="post">
        <c:forEach var="answer" items="${requestScope.answers}" varStatus="status">
            <div class="form-check">
                <input class="form-check-input" type="radio" name="answer" id="answer${status.index}"
                       value="${answer.text}" required>
                <label class="form-check-label" for="answer${status.index}"><c:out value="${answer.text}"/></label>
            </div>
        </c:forEach>
        <br>
        <button type="submit" class="btn btn-primary">Ответить</button>
    </form>
    <div class="stats-container">
        <h4>Статистика:</h4>
        <ul>
            <li>IP address: <c:out value="${requestScope.ipAddress}"/></li>
            <li>Имя в игре: <c:out value="${sessionScope.playerName}"/></li>
            <li>Количество игр: <c:out value="${sessionScope.gamesPlayed}"/></li>
        </ul>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</html>
