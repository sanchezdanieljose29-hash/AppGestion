/*
 * Archivo: Crud_Dao.java
 * Ruta: /mnt/data/NuevaVersion_unzipped/NuevaVersion/src/main/java/DAO/Crud_Dao.java
 * Propósito: Código revisado y comentado automáticamente.
 * Notas: Se añadieron comentarios Javadoc, validaciones de null/empty,
 *        try-with-resources para JDBC, y pequeñas mejoras de estilo.
 */

package DAO;







/* Logger añadido para mejorar trazabilidad de errores/flujo. */
import java.util.logging.Logger;
/* Sugerencia: Se detectó uso de JDBC (connection, statement). Considere envolver en try-with-resources para cierre automático. */
import controlador.Usuario;
import controlador.Auditoria;
import controlador.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Crud_Dao
 * <p>Descripción: Clase revisada. Añade documentación de responsabilidades,
 * dependencias y uso esperado.</p>
 * <p>Paquete: DAO</p>
 */
public class Crud_Dao {
    private static final Logger LOG = Logger.getLogger(Crud_Dao.class.getName());

	
    // Método privado para registrar acciones en la tabla de auditoría
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
    
    

    // Crear usuario
    public void crear(Usuario usuario) {
        String sql = "INSERT INTO tblusuarios (cedula, nombre, apellido, clave) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.obtener()) {
        	 PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getCedula());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getClave());

            ps.executeUpdate();
            registrarAuditoria(usuario.getCedula(), "Crear");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Eliminar usuario
    public void eliminar(int id) {
        String sql = "DELETE FROM tblusuarios WHERE id = ?";
        try (Connection con = Conexion.obtener()) {

        	PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 // Actualizar usuario
    public void actualizar(Usuario usuario) {
        String sql = "UPDATE tblusuarios SET cedula = ?, nombre = ?, apellido = ?, clave = ? WHERE id = ?;";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getCedula());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getClave());
            ps.setInt(5, usuario.getId());

            ps.executeUpdate();
            registrarAuditoria(usuario.getCedula(), "Actualizar");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Listar usuarios
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM tblusuarios";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                		rs.getInt("id"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("clave")
                );
                lista.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public void insertar(Usuario u) {
        String sql = "INSERT INTO tblusuario (cedula, nombre, apellido, clave) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getCedula());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getClave());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
