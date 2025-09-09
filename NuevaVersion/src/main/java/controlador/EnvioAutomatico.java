package controlador;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EnvioAutomatico {

	private final String usuario = "sanchezdanieljose29@gmail.com"; // Tu Gmail
	private final String clave = "cafi occt ypce svdz"; // Contraseña de aplicación
	/**
	 * Método para enviar un correo con asunto y mensaje personalizado
	 */
	public boolean Automatico(String destino, String asunto, String mensajeTexto) {
		// Configuración SMTP de Gmail
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		// Sesión autenticada
		Session sesion = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuario, clave);
			}
		});

		try {
			// Crear mensaje
			Message message = new MimeMessage(sesion);
			message.setFrom(new InternetAddress(usuario));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
			message.setSubject(asunto);
			message.setText(mensajeTexto);

			// Enviar
			Transport.send(message);
			System.out.println("✅ Correo enviado a: " + destino);
			return true;

		} catch (MessagingException e) {
			System.err.println("❌ Error al enviar correo: " + e.getMessage());
			return false;
		}
	}

}
