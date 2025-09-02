package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost/bd_JavaWeb";
    private static final String USUARIO = "root";
    private static final String CLAVE = "2556229";

    public static Connection obtener() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("No se encontró el driver JDBC", ex);
        }
    }
}
