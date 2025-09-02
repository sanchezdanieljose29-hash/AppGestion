package servlets;

import DAO.Login_Dao;
import controlador.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SvLogin")
public class SvLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Recibir datos del formulario
        String cedula = request.getParameter("cedula");
        String clave = request.getParameter("clave");

        // 2. Usar DAO para validar credenciales
        Login_Dao dao = new Login_Dao();
        Usuario user = dao.login(cedula, clave);

        // 3. Validar resultado
        if (user != null) {
            // ✅ Credenciales correctas
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", user); // guardamos el objeto completo
            response.sendRedirect("SvPanel"); // redirige al panel
        } else {
            // ❌ Credenciales incorrectas
            response.sendRedirect("index.jsp?error=1");
        }
    }
}
