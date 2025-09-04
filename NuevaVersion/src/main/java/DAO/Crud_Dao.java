package DAO;

// Importación de clases necesarias para trabajar con la base de datos y para logging
import java.util.logging.Logger;
import controlador.Usuario;       // Clase modelo Usuario
import controlador.Auditoria;    // Clase Auditoria (no se usa activamente aquí)
import controlador.Conexion;     // Clase encargada de obtener la conexión a la BD
import java.sql.*;               // JDBC para operaciones con base de datos
import java.util.ArrayList;
import java.util.List;

public class Crud_Dao {

    // Logger para registrar información y errores (no se usa activamente, pero es buena práctica)
    private static final Logger LOG = Logger.getLogger(Crud_Dao.class.getName());

    /*
    // Método privado para registrar auditoría (comentado actualmente)
    // Su propósito era insertar un registro en la tabla de auditoría cada vez que se realiza una acción.
    private void registrarAuditoria(String cedula, String operacion) {
        String sql = "INSERT INTO auditoria (usuario_id, operacion) VALUES (?, ?)";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ps.setString(2, operacion);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

    // ---------------- MÉTODO: Crear Usuario ----------------
    public void crear(Usuario usuario) {
        String sql = "INSERT INTO tblusuarios (cedula, nombre, apellido, clave) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.obtener()) { // Se obtiene una conexión desde la clase Conexion
            PreparedStatement ps = con.prepareStatement(sql); // Se prepara la consulta SQL

            // Se asignan los parámetros del objeto Usuario a la consulta
            ps.setString(1, usuario.getCedula());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getClave());

            ps.executeUpdate(); // Se ejecuta la consulta (INSERT)

            // registrarAuditoria(usuario.getCedula(), "Crear"); // Comentado, pero registraría en auditoría
        } catch (Exception e) {
            e.printStackTrace(); // Imprime cualquier error ocurrido
        }
    }

    // ---------------- MÉTODO: Eliminar Usuario ----------------
    public void eliminar(int id) {
        String sql = "DELETE FROM tblusuarios WHERE id = ?";
        try (Connection con = Conexion.obtener()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id); // Se indica el ID del usuario a eliminar
            ps.executeUpdate(); // Se ejecuta la consulta DELETE
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- MÉTODO: Actualizar Usuario ----------------
    public void actualizar(Usuario usuario) {
        String sql = "UPDATE tblusuarios SET cedula = ?, nombre = ?, apellido = ?, clave = ? WHERE id = ?;";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Se asignan los nuevos valores desde el objeto Usuario
            ps.setString(1, usuario.getCedula());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getClave());
            ps.setInt(5, usuario.getId());

            ps.executeUpdate(); // Se ejecuta la actualización en la base de datos

            // registrarAuditoria(usuario.getCedula(), "Actualizar"); // Comentado
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- MÉTODO: Listar Usuarios ----------------
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>(); // Lista para almacenar los usuarios recuperados
        String sql = "SELECT * FROM tblusuarios"; // Consulta para obtener todos los usuarios

        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Se recorren los resultados y se construyen objetos Usuario
            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("id"),
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("clave")
                );
                lista.add(u); // Se añade cada usuario a la lista
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista; // Se devuelve la lista de usuarios
    }

    // ---------------- MÉTODO: Insertar Usuario (duplicado de crear) ----------------
    public void insertar(Usuario u) {
        String sql = "INSERT INTO tblusuario (cedula, nombre, apellido, clave) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getCedula());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getClave());

            ps.executeUpdate(); // Ejecuta el INSERT

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
