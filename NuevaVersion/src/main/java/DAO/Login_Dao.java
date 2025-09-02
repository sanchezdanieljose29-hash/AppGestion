package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controlador.Conexion;
import controlador.Usuario;

public class Login_Dao {

    public Usuario login(String cedula, String clave) {
        Usuario user = null;

        String sql = "SELECT * FROM tblusuarios WHERE cedula = ? AND clave = ?";
        try (
            Connection con = Conexion.obtener();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {
            pst.setString(1, cedula);
            pst.setString(2, clave);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setCedula(cedula);
                user.setClave(clave);
                // 👇 si tienes más campos, agrégalos
                // user.setNombre(rs.getString("nombre"));
                // user.setApellido(rs.getString("apellido"));
            }

        } catch (Exception e) {
            System.out.println("❌ Error en Login_Dao.login()");
            e.printStackTrace();
        }
        return user;
    }
}
