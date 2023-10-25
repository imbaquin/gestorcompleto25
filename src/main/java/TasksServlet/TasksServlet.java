package TasksServlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import util.FileUtil;

import java.io.IOException;

@WebServlet("/TasksServlet")
public class TasksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String projectDirectory = getServletContext().getRealPath("/");
        String tasksFilePath = projectDirectory + user.getUsername() + "-tasks.txt";

        if (user.getTasks().isEmpty()) {
            FileUtil.readTasksFromFile(user, tasksFilePath);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("tasks.jsp");
        dispatcher.forward(request, response);
    }
}

