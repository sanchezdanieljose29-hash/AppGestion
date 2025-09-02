package servlets;

import DAO.Crud_Dao;
import controlador.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SvPanel")
public class SvPanel extends HttpServlet {

    // LISTAR usuarios (cuando entras al panel)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Crud_Dao crud = new Crud_Dao();
        List<Usuario> usuarios = crud.listar();

        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("panel_de_control.jsp").forward(request, response);
    }

    // CREAR, EDITAR, ELIMINAR
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operacion = request.getParameter("operacion");
        Crud_Dao crud = new Crud_Dao();

        try {
            if ("crear".equalsIgnoreCase(operacion)) {
                String cedula = request.getParameter("cedula");
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String clave = request.getParameter("clave");

                Usuario u = new Usuario(cedula, nombre, apellido, clave);
                crud.insertar(u);

            } else if ("Actualizar".equalsIgnoreCase(operacion)) {
                String cedula = request.getParameter("cedula");
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String clave = request.getParameter("clave");
                int id = Integer.parseInt(request.getParameter("id"));

                Usuario u = new Usuario(id, cedula, nombre, apellido, clave);
                crud.actualizar(u);

            } else if ("eliminar".equalsIgnoreCase(operacion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                crud.eliminar(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Siempre vuelve al panel con la lista actualizada
        response.sendRedirect(request.getContextPath() + "/SvPanel");
    }
}
