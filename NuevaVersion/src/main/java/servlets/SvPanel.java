package servlets;

// Importación de clases necesarias
import DAO.Crud_Dao; // Clase DAO que gestiona operaciones CRUD con la base de datos
import controlador.Usuario; // Clase modelo que representa un usuario (objeto)
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Anotación que registra el servlet y define su URL de acceso
@WebServlet("/SvPanel")
public class SvPanel extends HttpServlet {

    /**
     * Método doGet: Se ejecuta cuando el usuario accede al servlet mediante una petición GET.
     * Su propósito aquí es LISTAR los usuarios y enviarlos al panel de control.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Crud_Dao crud = new Crud_Dao(); // Se crea una instancia del DAO para acceder a la base de datos
        List<Usuario> usuarios = crud.listar(); // Se obtiene la lista de usuarios desde la base de datos

        // Se guarda la lista como atributo en el request para que esté disponible en la vista JSP
        request.setAttribute("usuarios", usuarios);

        // Se redirige a la vista JSP con los datos cargados
        request.getRequestDispatcher("panel_de_control.jsp").forward(request, response);
    }

    /**
     * Método doPost: Se ejecuta cuando el usuario envía un formulario (POST).
     * Aquí se manejan tres operaciones: CREAR, ACTUALIZAR y ELIMINAR usuarios.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operacion = request.getParameter("operacion"); // Se obtiene la operación solicitada
        Crud_Dao crud = new Crud_Dao(); // Instancia del DAO para operaciones con la base de datos

        try {
            /**
             * Condicional 1: Crear un nuevo usuario
             * Se ejecuta si el valor del parámetro "operacion" es igual a "crear" (ignorando mayúsculas/minúsculas).
             */
            if ("crear".equalsIgnoreCase(operacion)) {
                // Se obtienen los parámetros del formulario
                String cedula = request.getParameter("cedula");
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String clave = request.getParameter("clave");

                // Se crea un objeto Usuario (modelo) y se envía al DAO para insertarlo en la base de datos
                Usuario u = new Usuario(cedula, nombre, apellido, clave);
                crud.insertar(u);

            /**
             * Condicional 2: Actualizar un usuario existente
             * Se ejecuta si el valor de "operacion" es "Actualizar".
             */
            } else if ("Actualizar".equalsIgnoreCase(operacion)) {
                // Se obtienen los nuevos datos del usuario desde el formulario
                String cedula = request.getParameter("cedula");
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String clave = request.getParameter("clave");
                int id = Integer.parseInt(request.getParameter("id")); // Se convierte el ID a entero

                // Se crea un objeto Usuario con los nuevos datos y se actualiza mediante el DAO
                Usuario u = new Usuario(id, cedula, nombre, apellido, clave);
                crud.actualizar(u);

            /**
             * Condicional 3: Eliminar un usuario
             * Se ejecuta si el valor de "operacion" es "eliminar".
             */
            } else if ("eliminar".equalsIgnoreCase(operacion)) {
                // Se obtiene el ID del usuario a eliminar y se convierte a entero
                int id = Integer.parseInt(request.getParameter("id"));

                // Se elimina el usuario mediante el DAO
                crud.eliminar(id);
            }

        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, se imprime la traza para depuración
        }

        // Después de cualquier operación, se redirige nuevamente al panel con la lista actualizada
        response.sendRedirect(request.getContextPath() + "/SvPanel");
    }
}
