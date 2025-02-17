<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="../css/my_style.css">

    <title>Результат игры</title>
</head>
<body>
<div class="container-result">
    <div class="card">
        <div class="card-header">
            <h1 class="mt-5 text-center ${requestScope.resultText.contains('Победа') ? 'win-style' : 'lose-style'}">
                <c:out value="${requestScope.resultText}"/>
            </h1>
        </div>
        <div class="card-body text-center">
            <p class="lead">Ты это заслужил</p>

            <form action="result" method="post" class="text-center">
                <button type="submit" class="btn btn-primary">Начать заново</button>
            </form>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</html>
