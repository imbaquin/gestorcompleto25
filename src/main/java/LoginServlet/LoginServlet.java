package LoginServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import model.User;
import util.FileUtil;

import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Obtén la ruta relativa al directorio del proyecto
        String projectDirectory = getServletContext().getRealPath("/");
        String usersFilePath = projectDirectory + "data" + File.separator + "users.txt";
        
        List<User> users = FileUtil.readFromFile(usersFilePath);

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                request.getSession().setAttribute("user", user);
                
                // Cargar las tareas del usuario
                String tasksFilePath = projectDirectory + username + "-tasks.txt";
                FileUtil.readTasksFromFile(user, tasksFilePath); // Ajustado para incluir el objeto 'user' como un argumento.

                response.sendRedirect("TasksServlet");
                return;
            }
        }

        response.sendRedirect("login.jsp?error=Credenciales%20invalidas");
    }
}
