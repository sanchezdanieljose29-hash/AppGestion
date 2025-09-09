package servlets;

import controlador.Mascotas;
import DAO.Crud_DaoMascotas;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/SvCrudMascotas")
public class SvCrudMascotas extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(SvCrudMascotas.class.getName());
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operacion = request.getParameter("operacion");
        Crud_DaoMascotas dao = new Crud_DaoMascotas();

        if (operacion == null || operacion.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        switch (operacion) {

            // ------------------ CREAR MASCOTA ------------------
            case "Crear": {
            	
            	String vacioIdCliente = request.getParameter("idCliente");
            	int idCliente = 0; // o -1, o cualquier valor por defecto que uses para "sin cliente"

            	
            	if (vacioIdCliente != null && !vacioIdCliente.trim().isEmpty()) {
                try {
                    String nombre = request.getParameter("nombre");
                    String tipo = request.getParameter("tipo");
                    String raza = request.getParameter("raza");
                    String sexo = request.getParameter("sexo");
                    double precioBase = Double.parseDouble(request.getParameter("precio_base"));
                    double precioVenta = Double.parseDouble(request.getParameter("precio_venta"));
                    String estado = request.getParameter("estado");
                    

                    Mascotas mascota = new Mascotas(nombre, tipo, raza, sexo, precioBase, precioVenta, estado, idCliente);
                    dao.crear(mascota);

                } catch (Exception e) {
                    LOG.warning("❌ Error al crear mascota: " + e.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/SvPanelMascotas");
                break;
            }
            	
            }

            // ------------------ ELIMINAR MASCOTA ------------------
            case "Eliminar": {
                String idStr = request.getParameter("id");

                if (idStr != null && !idStr.trim().isEmpty()) {
                    try {
                        int id = Integer.parseInt(idStr.trim());
                        dao.eliminar(id);
                    } catch (NumberFormatException e) {
                        LOG.warning("⚠️ ID inválido para eliminar mascota: " + idStr);
                    }
                } else {
                    LOG.warning("❌ ID nulo o vacío para eliminar mascota");
                }

                response.sendRedirect(request.getContextPath() + "/SvPanelMascotas");
                break;
            }

            // ------------------ ACTUALIZAR MASCOTA ------------------
            case "Actualizar": {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    String nombre = request.getParameter("nombre");
                    String tipo = request.getParameter("tipo");
                    String raza = request.getParameter("raza");
                    String sexo = request.getParameter("sexo");
                    double precioBase = Double.parseDouble(request.getParameter("precio_base"));
                    double precioVenta = Double.parseDouble(request.getParameter("precio_venta"));
                    String estado = request.getParameter("estado");
                    int idCliente = Integer.parseInt(request.getParameter("id_cliente"));

                    Mascotas mascota = new Mascotas(id, nombre, tipo, raza, sexo, precioBase, precioVenta, estado, idCliente);
                    dao.actualizar(mascota);

                } catch (Exception e) {
                    LOG.warning("❌ Error al actualizar mascota: " + e.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/SvPanelMascotas");
                break;
            }

            // ------------------ OPERACIÓN NO VÁLIDA ------------------
            default:
                LOG.warning("⚠ Operación de mascota no reconocida: " + operacion);
                response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
