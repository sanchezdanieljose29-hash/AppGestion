/*
 * Archivo: SvCrud.java
 * Propósito: Servlet controlador para operaciones CRUD sobre usuarios.
 * Autor: Daniel (mejorado con validaciones y buenas prácticas).
 */

package servlets;

import controlador.Usuario;
import DAO.Crud_Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/SvCrud")
public class SvCrud extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SvCrud.class.getName());
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operacion = request.getParameter("operacion");
        Crud_Dao dao = new Crud_Dao();

        if (operacion == null || operacion.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        switch (operacion) {
            case "Crear": {
                String cedula = request.getParameter("cedula");
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String clave = request.getParameter("clave");

                if (cedula != null && nombre != null && apellido != null && clave != null) {
                    Usuario usuario = new Usuario(cedula, nombre, apellido, clave);
                    dao.crear(usuario);
                } else {
                    LOG.warning("❌ No se pudo crear: parámetros incompletos");
                }

                response.sendRedirect(request.getContextPath() + "/SvPanel");
                break;
            }

            case "Eliminar": {
                String idStr = request.getParameter("id");

                if (idStr != null && !idStr.trim().isEmpty()) {
                    try {
                        int id = Integer.parseInt(idStr.trim());
                        dao.eliminar(id);
                    } catch (NumberFormatException e) {
                        LOG.warning("⚠️ ID inválido: " + idStr);
                    }
                } else {
                    LOG.warning("❌ No se pudo eliminar: id nulo o vacío");
                }

                response.sendRedirect(request.getContextPath() + "/SvPanel");
                break;
            }


            case "Actualizar": {
                String idStr = request.getParameter("id");
                if (idStr != null) {
                    int id = Integer.parseInt(idStr);

                    String cedula = request.getParameter("cedula");
                    String nombre = request.getParameter("nombre");
                    String apellido = request.getParameter("apellido");
                    String clave = request.getParameter("clave");

                    Usuario usuario = new Usuario(id, cedula, nombre, apellido, clave);
                    dao.actualizar(usuario);
                    
                } else {
                    LOG.warning("❌ No se pudo actualizar: parámetros incompletos");
                }

                response.sendRedirect(request.getContextPath() + "/SvPanel");
                break;
            }

            default:
                LOG.warning("⚠ Operación no reconocida: " + operacion);
                response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
