package DAO;

import controlador.EnvioAutomatico;
import controlador.Mascotas;

public class MensajeActualizacionMascota {

    public static void notificarActualizacion(Mascotas mascota) {
        // Construir asunto y mensaje
        String asunto = "Mascota Actualizada";
        String mensaje = "Se ha actualizado una mascota en el sistema:\n\n" +
                         "ID: " + mascota.getId() + "\n" +
                         "Nombre: " + mascota.getNombre() + "\n" +
                         "Tipo: " + mascota.getTipo() + "\n" +
                         "Raza: " + mascota.getRaza() + "\n" +
                         "Sexo: " + mascota.getSexo() + "\n" +
                         "Precio Base: $" + mascota.getPrecioBase() + "\n" +
                         "Precio Venta: $" + mascota.getPrecioVenta() + "\n" +
                         "Estado: " + mascota.getEstado() + "\n" +
                         "ID Cliente: " + mascota.getIdCliente();

        // Destino del correo (puedes modificarlo si tienes un campo de correo en Mascotas)
        String destino = "sanchezdanieljose29@gmail.com";

        // Crear instancia del envío automático
        EnvioAutomatico automatico = new EnvioAutomatico();

        // Enviar correo
        boolean enviado = automatico.Automatico(destino, asunto, mensaje);

        if (enviado) {
            System.out.println("✅ Notificación de actualización enviada.");
        } else {
            System.out.println("❌ No se pudo enviar la notificación.");
        }
    }
}
