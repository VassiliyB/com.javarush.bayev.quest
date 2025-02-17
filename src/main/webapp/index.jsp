<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Текстовый квест</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="../css/my_style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container-start">
    <div class="card">
        <div class="card-header">
            <h1 class="text-center">Добро пожаловать!</h1>
        </div>
        <div class="card-body">
            <p class="lead text-center">Пожалуйста, введите ваше имя:</p>

            <form action="game" method="post">
                <div class="mb-3">
                    <label for="playerName" class="form-label">Имя:</label>
                    <input type="text" class="form-control" id="playerName" name="playerName" required>
                </div>
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">Начать игру</button>
                </div>
            </form>
        </div>

    </div>
</div>
</body>
</html>