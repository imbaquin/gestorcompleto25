package RegisterServlet;

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

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cedula = request.getParameter("cedula");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Verificar si la cédula y el nombre de usuario contienen espacios
        if (cedula.contains(" ") || username.contains(" ")) {
            response.sendRedirect("register.jsp?error=No se permiten espacios");
            return;
        }

        // Verificar si la cédula contiene solo números
        if (!cedula.matches("\\d+")) {
            response.sendRedirect("register.jsp?error=La cédula solo puede contener números");
            return;
        }

        // Obtén la ruta relativa al directorio del proyecto
        String projectDirectory = getServletContext().getRealPath("/");
        String filePath = projectDirectory + "data" + File.separator + "users.txt";

        List<User> users = FileUtil.readFromFile(filePath);
        for (User user : users) {
            if (user.getCedula().equals(cedula)) {
                response.sendRedirect("register.jsp?error=Cédula ya registrada");
                return;
            } else if (user.getUsername().equals(username)) {
                response.sendRedirect("register.jsp?error=Nombre de usuario ya registrado");
                return;
            }
        }

        User user = new User(cedula, username, password);
        FileUtil.writeToFile(user.toString(), filePath);

        response.sendRedirect("login.jsp");
    }
}