<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>Bienvenido a ToDoListApp</title>
    <style>
        
        body {
            background-color: #f8f9fa; /* Cambia el color de fondo del cuerpo de la página */
        }

        .container {
            background-color: #ffffff; /* Cambia el color de fondo del contenedor */
            padding: 20px;
            border-radius: 10px; /* Agrega bordes redondeados al contenedor */
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1); /* Agrega una sombra al contenedor */
            margin-top: 50px; /* Ajusta el margen superior */
        }

        h1 {
            color: #007bff; /* Cambia el color del título */
        }

        p {
            color: #6c757d; /* Cambia el color del texto */
        }

        .btn-primary, .btn-secondary {
            margin-top: 10px; /* Ajusta el margen superior de los botones */
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Bienvenido a ToDoListApp</h1>
    <p>Una aplicación simple para gestionar tus tareas.</p>
    <div>
        <!-- Botón para navegar a la página de inicio de sesión -->
        <a href="login.jsp" class="btn btn-primary">Iniciar Sesión</a>
        <!-- Botón para navegar a la página de registro -->
        <a href="register.jsp" class="btn btn-secondary">Registrarse</a>
    </div>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>
