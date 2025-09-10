package DAO;

import controlador.Mascotas;
import controlador.Usuario;
import controlador.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Crud_DaoMascotas {

    private static final Logger LOG = Logger.getLogger(Crud_DaoMascotas.class.getName());

    // ---------------- MÉTODO: Crear Mascota ----------------
    public void crear(Mascotas mascota) {

        // Consulta SQL para insertar una nueva mascota en la tabla
        String sql = "INSERT INTO tblmascotas (nombre, tipo, raza, sexo, precioBase, precioVenta, estado, idCliente) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Se asignan los valores a la consulta usando los datos del objeto Mascotas
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getTipo());
            ps.setString(3, mascota.getRaza());
            ps.setString(4, mascota.getSexo());
            ps.setDouble(5, mascota.getPrecioBase());
            ps.setDouble(6, mascota.getPrecioVenta());
            ps.setString(7, mascota.getEstado());
            ps.setInt(8, mascota.getIdCliente());

            // Se ejecuta la inserción
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- MÉTODO: Actualizar Mascota ----------------
    public void actualizar(Mascotas mascota) {

        /*
        ObtenerDeActualizacionMascota helper = new ObtenerDeActualizacionMascota();
        Usuario usuario = helper.obtenerPorId(mascota.getId()); */

        // Consulta SQL para actualizar los datos de la mascota
        String sql = "UPDATE tblmascotas SET nombre = ?, tipo = ?, raza = ?, sexo = ?, " +
                     "precioBase = ?, precioVenta = ?, estado = ?, idCliente = ? WHERE id = ?";

        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Se asignan los valores a actualizar usando el objeto recibido
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getTipo());
            ps.setString(3, mascota.getRaza());
            ps.setString(4, mascota.getSexo());
            ps.setDouble(5, mascota.getPrecioBase());
            ps.setDouble(6, mascota.getPrecioVenta());
            ps.setString(7, mascota.getEstado());
            ps.setInt(8, mascota.getIdCliente());
            ps.setInt(9, mascota.getId()); // ID de la mascota a actualizar

            // Se ejecuta la actualización
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- MÉTODO: Eliminar Mascota ----------------
    public void eliminar(int id) {

        // Consulta SQL para eliminar una mascota por su ID
        String sql = "DELETE FROM tblmascotas WHERE id = ?";

        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Se pasa el ID como parámetro
            ps.setInt(1, id);

            // Se ejecuta la eliminación
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- MÉTODO: Listar Mascotas ----------------
    public List<Mascotas> listar() {

        // ArrayList que almacenará las mascotas recuperadas
        List<Mascotas> lista = new ArrayList<>();
        String sql = "SELECT * FROM tblmascotas";

        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Se recorre el resultado y se crea una lista de objetos Mascotas
            while (rs.next()) {
                Mascotas m = new Mascotas(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getString("raza"),
                    rs.getString("sexo"),
                    rs.getDouble("precioBase"),
                    rs.getDouble("precioVenta"),
                    rs.getString("estado"),
                    rs.getInt("idCliente")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /*
    // ---------------- MÉTODO: Obtener por ID ----------------
    public Mascotas obtenerPorId(int id) {
        String sql = "SELECT * FROM tblmascotas WHERE id = ?";
        Mascotas mascota = null;

        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                mascota = new Mascotas(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getString("raza"),
                    rs.getString("sexo"),
                    rs.getDouble("precio_base"),
                    rs.getDouble("precio_venta"),
                    rs.getString("estado"),
                    rs.getInt("id_cliente")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mascota;
    }
    */

    /*
    // ---------------- MÉTODO: Eliminar Usuario (OBSOLETO) ----------------
    public void eliminar(int id) {
        // Se consulta el usuario antes de eliminarlo
        ObtenerDeEliminacion helper = new ObtenerDeEliminacion();
        Usuario usuario = helper.obtenerPorId(id); // 👈 Este método busca un usuario

        if (usuario == null) {
            System.out.println("❌ Usuario no encontrado con ID " + id);
            return;
        }

        String sql = "DELETE FROM tblusuarios WHERE id = ?";
        try (Connection con = Conexion.obtener()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id); // Se indica el ID del usuario a eliminar
            ps.executeUpdate(); // Se ejecuta la consulta DELETE

            System.out.println("✅ Usuario eliminado: " + usuario.getNombre());

            // Se notifica la eliminación por correo
            DaoMensajeAutomatico envio = new DaoMensajeAutomatico();
            envio.notificarEliminacion(usuario);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
