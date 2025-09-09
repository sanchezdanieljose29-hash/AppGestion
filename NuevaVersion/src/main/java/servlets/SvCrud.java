package servlets;

// Importación de clases necesarias
import controlador.Usuario;       // Clase modelo Usuario
import DAO.Crud_Dao;              // Clase que contiene los métodos CRUD
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

// Anotación que define la URL de acceso al servlet
@WebServlet("/SvCrud")
public class SvCrud extends HttpServlet {
    
    // Logger para imprimir mensajes en consola (advertencias, errores, etc.)
    private static final Logger LOG = Logger.getLogger(SvCrud.class.getName());

    // Identificador único de la clase serializable (buena práctica en servlets)
    private static final long serialVersionUID = 1L;

    /**COMENTARIO DEL CRUD
     * Métodos que maneja peticiones POST (formularios).
     * Se espera que los formularios envíen un campo oculto o botón con el valor Crear, Actualizar o Eliminar.
     * Si no se recibe nada, redirige al error:
     * 
     */
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Se obtiene el valor del parámetro "operacion" enviado desde el formulario
        String operacion = request.getParameter("operacion");

        // Instancia del DAO para ejecutar las operaciones de base de datos
        Crud_Dao dao = new Crud_Dao();

        // ✅ Validación básica: si no se envió ninguna operación, redirige a una página de error
        if (operacion == null || operacion.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        // 🧠 Estructura switch que decide qué operación ejecutar según el valor de "operacion"
        switch (operacion) {

            // --------------------- CASE: CREAR ---------------------
            case "Crear": {
                // Se obtienen los parámetros del formulario
                String cedula = request.getParameter("cedula");
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String clave = request.getParameter("clave");

                // Validación: Se asegura de que ningún parámetro esté nulo
                if (cedula != null && nombre != null && apellido != null && clave != null) {
                    // Se crea un nuevo objeto Usuario y se guarda usando el DAO
                    Usuario usuario = new Usuario(cedula, nombre, apellido, clave);
                    dao.crear(usuario);
                } else {
                    // Si falta algún dato, se muestra una advertencia en consola
                    LOG.warning("❌ No se pudo crear: parámetros incompletos");
                }

                // Después de la operación, redirige al panel de control
                response.sendRedirect(request.getContextPath() + "/SvPanel");
                break;
            }

         // --------------------- CASE: ELIMINAR ---------------------
            case "Eliminar": {
                String idStr = request.getParameter("id");

                // Validación: se asegura de que el ID no sea nulo ni vacío
                if (idStr != null && !idStr.trim().isEmpty()) {
                    try {
                        // Intenta convertir el ID a entero
                        int id = Integer.parseInt(idStr.trim());
                        dao.eliminar(id); // Elimina el usuario
                    } catch (NumberFormatException e) {
                        // Si el ID no es un número válido, muestra advertencia
                        LOG.warning("⚠️ ID inválido: " + idStr);
                    }
                } else {
                    LOG.warning("❌ No se pudo eliminar: id nulo o vacío");
                }

                // Redirige al panel después de la operación
                response.sendRedirect(request.getContextPath() + "/SvPanel");
                break;
            }

            // --------------------- CASE: ACTUALIZAR ---------------------
            case "Actualizar": {
                String idStr = request.getParameter("id");

                // Validación: se asegura de que el ID no sea nulo
                if (idStr != null) {
                    int id = Integer.parseInt(idStr); // Conversión segura, ya validada

                    // Obtiene los nuevos datos del formulario
                    String cedula = request.getParameter("cedula");
                    String nombre = request.getParameter("nombre");
                    String apellido = request.getParameter("apellido");
                    String clave = request.getParameter("clave");

                    // Se crea un nuevo objeto Usuario con ID para actualizarlo
                    Usuario usuario = new Usuario(id, cedula, nombre, apellido, clave);
                    dao.actualizar(usuario); // Se llama al método de actualización
                } else {
                    LOG.warning("❌ No se pudo actualizar: parámetros incompletos");
                }

                // Redirige al panel después de la operación
                response.sendRedirect(request.getContextPath() + "/SvPanel");
                break;
            }

            // --------------------- CASE: DEFAULT ---------------------
            // Si la operación no coincide con ninguno de los casos anteriores
            default:
                LOG.warning("⚠ Operación no reconocida: " + operacion);
                response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}