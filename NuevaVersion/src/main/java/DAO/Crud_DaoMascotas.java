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
        String sql = "INSERT INTO tblmascotas (nombre, tipo, raza, sexo, precio_base, precio_venta, estado, id_cliente) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getTipo());
            ps.setString(3, mascota.getRaza());
            ps.setString(4, mascota.getSexo());
            ps.setDouble(5, mascota.getPrecioBase());
            ps.setDouble(6, mascota.getPrecioVenta());
            ps.setString(7, mascota.getEstado());
            ps.setInt(8, mascota.getIdCliente());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- MÉTODO: Actualizar Mascota ----------------
    public void actualizar(Mascotas mascota) {
        String sql = "UPDATE tblmascotas SET nombre = ?, tipo = ?, raza = ?, sexo = ?, " +
                     "precio_base = ?, precio_venta = ?, estado = ?, id_cliente = ? WHERE id = ?";

        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getTipo());
            ps.setString(3, mascota.getRaza());
            ps.setString(4, mascota.getSexo());
            ps.setDouble(5, mascota.getPrecioBase());
            ps.setDouble(6, mascota.getPrecioVenta());
            ps.setString(7, mascota.getEstado());
            ps.setInt(8, mascota.getIdCliente());
            ps.setInt(9, mascota.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- MÉTODO: Eliminar Mascota ----------------
    public void eliminar(int id) {
        String sql = "DELETE FROM tblmascotas WHERE id = ?";

        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- MÉTODO: Listar Mascotas ----------------
    public List<Mascotas> listar() {
        List<Mascotas> lista = new ArrayList<>();
        String sql = "SELECT * FROM tblmascotas";

        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            	Mascotas m = new Mascotas(
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
                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

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
    
    
    
}