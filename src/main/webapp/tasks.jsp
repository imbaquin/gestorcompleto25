<%@page import="model.Task"%>
<%@page import="util.LinkedList"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Include Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <!-- Include Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

        <title>Tareas de <%= ((User) session.getAttribute("user")).getUsername()%></title>
    </head>
    <body>
        <a href="login.jsp" class="btn btn-link">
            <i class="fas fa-sign-out-alt fa-2x"></i>
        </a>

        <%
            String error = request.getParameter("error");
            if (error != null) {
        %>
        <div class="alert alert-danger" role="alert">
            <%= error%>
        </div>
        <%
            }
        %>

        <div class="container mt-5">
            <h1>Bienvenido <%= ((User) session.getAttribute("user")).getUsername()%>!</h1>

            <table class="table table-bordered mt-4">
                <thead class="thead-light">
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Descripción</th>
                        <th>Fecha de Vencimiento</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        LinkedList<Task> tasks = ((User) session.getAttribute("user")).getTasks();
                        for (int i = 0; i < tasks.size(); i++) {
                            Task task = tasks.get(i);
                    %>
                    <tr>
                        <td><%= task.getId()%></td>
                        <td><%= task.getTitle()%></td>
                        <td><%= task.getDescription()%></td>
                        <td><%= task.getDueDate()%></td>
                        <td>
                            <a href="tasks.jsp?editTaskId=<%= task.getId()%>" class="btn btn-warning btn-sm">Editar</a>
                            <form action="deleteTask" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= task.getId()%>">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que quieres eliminar esta tarea?');">Eliminar</button>
                            </form>


                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>


            <%
                String editTaskId = request.getParameter("editTaskId");
                if (editTaskId != null) {
                    Task editTask = null;
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        if (task.getId() == Integer.parseInt(editTaskId)) {
                            editTask = task;
                            break;
                        }
                    }
                    if (editTask != null) {
            %>
            <!-- Formulario para editar tarea existente -->
            <div class="my-4">
                <h3>Editar Tarea</h3>
                <form action="editTask" method="post">
                    <input type="hidden" name="id" value="<%= editTask.getId()%>">
                    <div class="form-group">
                        <label for="title">Título:</label>
                        <input type="text" class="form-control" id="title" name="title" value="<%= editTask.getTitle()%>" required>
                    </div>
                    <div class="form-group">
                        <label for="description">Descripción:</label>
                        <input type="text" class="form-control" id="description" name="description" value="<%= editTask.getDescription()%>" required>
                    </div>
                    <div class="form-group">
                        <label for="dueDate">Fecha de Vencimiento:</label>
                        <input type="date" class="form-control" id="dueDate" name="dueDate" value="<%= editTask.getDueDate().toString()%>" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Actualizar Tarea</button>
                </form>
            </div>
            <%
                }
            } else {
            %>
            <!-- Formulario para crear nueva tarea -->
            <div class="my-4">
                <h3>Crear nueva tarea</h3>
                <form action="createTask" method="post">
                    <div class="form-group">
                        <label for="id">ID:</label>
                        <input type="number" class="form-control" id="id" name="id" 
                               value="<%= ((User) session.getAttribute("user")).getTasks().size() + 1%>" 
                               required>
                    </div>
                    <div class="form-group">
                        <label for="title">Título:</label>
                        <input type="text" class="form-control" id="title" name="title" required>
                    </div>
                    <div class="form-group">
                        <label for="description">Descripción:</label>
                        <input type="text" class="form-control" id="description" name="description" required>
                    </div>
                    <div class="form-group">
                        <label for="dueDate">Fecha de Vencimiento:</label>
                        <input type="date" class="form-control" id="dueDate" name="dueDate" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Crear Tarea</button>
                </form>
            </div>
            <%
                }
            %>



        </div>

        <!-- Opcional: incluye el JS de Bootstrap y sus dependencias si los necesitas -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
