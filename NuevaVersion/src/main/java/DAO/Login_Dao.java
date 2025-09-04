package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controlador.Conexion;
import controlador.Usuario;

public class Login_Dao {
	
	/*Si encuentra un registro en la base de datos que coincida con la cédula y la clave proporcionadas,
	 *  devuelve un objeto Usuario. Si no, devuelve null.
	 */

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
                
            }

        } catch (Exception e) {
            System.out.println(" Error en Login_Dao.login()");
            e.printStackTrace();
        }
        return user;
    }
}
