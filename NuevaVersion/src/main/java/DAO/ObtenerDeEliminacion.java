package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controlador.Conexion;
import controlador.Usuario;

public class ObtenerDeEliminacion {
	
	public Usuario obtenerPorId(int id) {
	    String sql = "SELECT id, cedula, nombre, apellido FROM tblusuarios WHERE id = ?";
	    try (Connection con = Conexion.obtener();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();	

	        if (rs.next()) {
	            Usuario u = new Usuario();
	            u.setId(rs.getInt("id"));
	            u.setCedula(rs.getString("cedula"));
	            u.setNombre(rs.getString("nombre"));
	            u.setApellido(rs.getString("apellido"));
	            return u;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}


}