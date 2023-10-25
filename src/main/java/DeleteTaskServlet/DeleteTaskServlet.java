package DeleteTaskServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Task;
import model.User;
import util.FileUtil;

import java.io.IOException;

@WebServlet(name = "DeleteTaskServlet", urlPatterns = {"/deleteTask"})
public class DeleteTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int taskId = Integer.parseInt(request.getParameter("id"));

        boolean taskRemoved = false;

        // Eliminar la tarea basada en su ID y no en su índice
        for (int i = 0; i < user.getTasks().size(); i++) {
            Task task = user.getTasks().get(i);
            if (task.getId() == taskId) {
                user.getTasks().remove(i);
                taskRemoved = true;
                break;
            }
        }

        if (taskRemoved) {
            // Actualizar el archivo de tareas
            FileUtil.writeTasksToFile(user, getServletContext().getRealPath("/") + user.getUsername() + "-tasks.txt");
        } else {
            // Manejar el caso cuando no se encuentra el ID de la tarea
            response.sendRedirect("tasks.jsp?error=Task not found");
            return;
        }

        response.sendRedirect("tasks.jsp");
    }
}
