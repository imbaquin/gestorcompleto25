package EditTaskServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Task;
import model.User;
import util.FileUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "EditTaskServlet", urlPatterns = {"/editTask"})
public class EditTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Date dueDate;

        try {
            dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dueDate"));
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendRedirect("tasks.jsp?error=Invalid date format");
            return;
        }

        // Actualizar la tarea existente
        for (int i = 0; i < user.getTasks().size(); i++) {
            Task task = user.getTasks().get(i);
            if (task.getId() == id) {
                task.setTitle(title);
                task.setDescription(description);
                task.setDueDate(dueDate);
                break;
            }
        }

        // Actualizar el archivo de tareas
        FileUtil.writeTasksToFile(user, getServletContext().getRealPath("/") + user.getUsername() + "-tasks.txt");

        response.sendRedirect("tasks.jsp");
    }
}
