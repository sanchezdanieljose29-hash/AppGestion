package DAO;

import controlador.EnvioAutomatico;
import controlador.Usuario;

public class DaoMensajeAutomatico {
	
    public static void notificarEliminacion(Usuario usuario) {
        // Construir asunto y mensaje
        String asunto = "Usuario Eliminado";
        String mensaje = "Se ha eliminado un usuario del sistema:\n\n" +
                         "ID: " + usuario.getId() + "\n" +
                         "Cédula: " + usuario.getCedula() + "\n" +
                         "Nombre: " + usuario.getNombre() + " " + usuario.getApellido();

        // Aquí decides a qué correo enviar
        String destino = "sanchezdanieljose29@gmail.com"; // o podrías usar usuario.getCorreo() si lo tienes

        // Crear instancia de la clase que envía
        EnvioAutomatico automatico = new EnvioAutomatico();

        // Llamar al método que manda el correo
        boolean enviado = automatico.Automatico(destino, asunto, mensaje);

        if (enviado) {
            System.out.println("✅ Notificación de eliminación enviada.");
        } else {
            System.out.println("❌ No se pudo enviar la notificación.");
        }
    }
}