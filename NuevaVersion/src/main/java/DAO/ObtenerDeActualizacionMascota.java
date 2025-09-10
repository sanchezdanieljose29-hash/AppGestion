package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controlador.Conexion;
import controlador.Mascotas;

public class ObtenerDeActualizacionMascota {

	public Mascotas obtenerPorId(int id) {
		String sql = "SELECT id, nombre, precioBase, precioVenta, estado, idCliente FROM tblmascotas WHERE id = ?";
		try (Connection con = Conexion.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Mascotas u = new Mascotas();
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("nombre"));
				u.setPrecioBase(rs.getDouble("precioBase"));
				u.setPrecioVenta(rs.getDouble("precioVenta"));
				u.setEstado(rs.getString("estado"));
				u.setIdCliente(rs.getInt("idCliente"));
				return u;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}