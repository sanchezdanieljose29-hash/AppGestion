package servlets;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Properties;

@WebServlet("/EnvioCorreo")
public class EnvioCorreo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EnvioCorreo() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Correo destinatario
        String to = "sanchezdanieljose29@gmail.com";
        // Correo remitente
        String from = "sanchezdanieljose29@gmail.com";

        // Credenciales (usuario y contraseña de aplicación)
        final String username = "sanchezdanieljose29@gmail.com"; // Cambia esto por tu usuario SMTP real
        final String password = "cafi occt ypce svdz"; // Cambia por tu contraseña de aplicación

        // Servidor SMTP (Mailtrap en este caso)
        String host = "smtp.gmail.com";


        // Configuración SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); // Requiere autenticación
        props.put("mail.smtp.starttls.enable", "true"); // Habilitar TLS
        props.put("mail.smtp.host", host); // Host SMTP
        props.put("mail.smtp.port", "587"); // Puerto SMTP (TLS)

        // Crear sesión con autenticación
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Crear mensaje MIME
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from)); // Remitente
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); // Destinatario
            message.setSubject("Hello from the Mailtrap team"); // Asunto
            message.setText("Enviaste un correo electronico desde Jakarta---AGUUUU--GUUUU---u-u!"); // Contenido

            // Enviar mensaje
            Transport.send(message);

            System.out.println("Email Message Sent Successfully!");
            
            // Opcional: enviar respuesta HTTP
            response.getWriter().write("Correo enviado correctamente");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new ServletException("Error al enviar correo", e);
        }
    }
}