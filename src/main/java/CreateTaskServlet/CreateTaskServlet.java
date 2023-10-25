package CreateTaskServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Task;
import model.User;
import util.FileUtil;
import util.LinkedList;

@WebServlet(name = "CreateTaskServlet", urlPatterns = {"/createTask"})
public class CreateTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        // Añadiendo validación para el ID
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("tasks.jsp?error=ID inválido");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            response.sendRedirect("tasks.jsp?error=ID inválido");
            return;
        }

        // Validar si existe una tarea con el mismo ID

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Date dueDate = null;
        try {
            dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dueDate"));
        } catch (ParseException ex) {
            Logger.getLogger(CreateTaskServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("tasks.jsp?error=Fecha inválida");
            return;
        }

        Task newTask = new Task(id, title, description, dueDate);
        user.getTasks().add(newTask);

        // Guardar la tarea en el archivo
        FileUtil.writeTasksToFile(user, getServletContext().getRealPath("/") + user.getUsername() + "-tasks.txt");

        response.sendRedirect("tasks.jsp");
    }
}
