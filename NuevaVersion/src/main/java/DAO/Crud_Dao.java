package DAO;

// Importación de clases necesarias para trabajar con la base de datos y para logging
import java.util.logging.Logger;
import controlador.Usuario; // Clase modelo Usuario
import controlador.Auditoria; // Clase Auditoria (no se usa activamente aquí)
import controlador.Conexion; // Clase encargada de obtener la conexión a la BD
import java.sql.*; // JDBC para operaciones con base de datos
import java.util.ArrayList;
import java.util.List;

//Crud_Dao es una clase que encapsula toda la lógica para acceder a la base de datos (crear, eliminar, actualizar usuarios).
public class Crud_Dao {

	private static final Logger LOG = Logger.getLogger(Crud_Dao.class.getName());

	/*
	 * Método privado para registrar auditoría (comentado actualmente) Su propósito
	 * era insertar un registro en la tabla de auditoría cada vez que se realiza una
	 * acción. private void insertar(String cedula, String operacion) { String sql =
	 * "INSERT INTO auditoria (usuario_id, operacion) VALUES (?, ?)"; try
	 * (Connection con = Conexion.obtener(); PreparedStatement ps =
	 * con.prepareStatement(sql)) { ps.setString(1, cedula); ps.setString(2,
	 * operacion); ps.executeUpdate(); } catch (Exception e) { e.printStackTrace();
	 * } }
	 */

	/*
	 * Este método recibe un objeto de la clase Usuario como parámetro y usa sus
	 * datos para insertar una fila en la tabla tblusuarios de la base de datos. No
	 * retorna nada (void), pero su efecto es persistir(Guardar los datos de forma
	 * permanente) el objeto en la base de datos
	 */
	public void crear(Usuario usuario) {
		String sql = "INSERT INTO tblusuarios (cedula, nombre, apellido, clave) VALUES (?, ?, ?, ?)";
		try (Connection con = Conexion.obtener()) { // Se obtiene una conexión desde la clase Conexion
			PreparedStatement ps = con.prepareStatement(sql); // Se prepara la consulta SQL

			/* Aquí se extraen los atributos del objeto Usuario mediante sus métodos get.
			 * Estos valores reemplazan los ? del SQL.
			 */
			ps.setString(1, usuario.getCedula());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getApellido());
			ps.setString(4, usuario.getClave());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace(); // Imprime cualquier error ocurrido
		}
	}

	// ---------------- MÉTODO: Eliminar Usuario ----------------
	public void eliminar(int id) {
		// 1. Consultar el usuario antes de borrarlo
		ObtenerDeEliminacion helper = new ObtenerDeEliminacion();
		Usuario usuario = helper.obtenerPorId(id); // 👈 se llama desde la otra clase

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
			DaoMensajeAutomatico envio = new DaoMensajeAutomatico();
			envio.notificarEliminacion(usuario);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------- MÉTODO: Actualizar Usuario ----------------
	public void actualizar(Usuario usuario) {
		String sql = "UPDATE tblusuarios SET cedula = ?, nombre = ?, apellido = ?, clave = ? WHERE id = ?;";
		try (Connection con = Conexion.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {

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
		List<Usuario> lista = new ArrayList<>();
		String sql = "SELECT * FROM tblusuarios"; // Consulta para obtener todos los usuarios

		try (Connection con = Conexion.obtener();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			/*
			 * rs.next() avanza a la siguiente fila de resultados en la tabla por cada fila
			 * encontrada, vamos a crear un objeto Usuario.
			 */
			while (rs.next()) {
				Usuario u = new Usuario(rs.getInt("id"), rs.getString("cedula"), rs.getString("nombre"),
						rs.getString("apellido"), rs.getString("clave"));
				lista.add(u); // Se añade cada usuario a la lista
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista; /*
			 * Se devuelve la lista completa de objetos Usuario al metodo listar Servlet Crud (SvCrud) 
			 *  se utiliza esta lista para mostrar los datos en una en el PanelControlMascotas */
	}

}