package servlets;

// Importamos clases para manejar conexión a base de datos
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

// Importamos la librería iText para crear PDFs
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

// Importamos clases de conexión y servlet
import controlador.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

// Definimos el servlet con la URL /ReporteClientespdf
@WebServlet("/ReporteClientespdf")
public class ReportePdf extends HttpServlet {
    
    private static final Logger LOG = Logger.getLogger(ReportePdf.class.getName());
    private static final long serialVersionUID = 1L;

    // Método que responde a peticiones GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Definimos que la respuesta es un archivo PDF
        response.setContentType("application/pdf");
        // Indicamos que el archivo será descargado con este nombre
        response.setHeader("Content-Disposition", "attachment; filename=ReporteClientes.pdf");

        try {
            // Creamos un documento PDF vacío
            Document document = new Document();
            // Obtenemos un escritor PDF que envía la salida directamente al navegador
            PdfWriter.getInstance(document, response.getOutputStream());
            // Abrimos el documento para empezar a escribir en él
            document.open();

            // Creamos un título con fuente Helvetica negrita tamaño 18, color negro
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Reporte de Clientes Registrados", titleFont);
            // Centramos el título
            title.setAlignment(Element.ALIGN_CENTER);
            // Añadimos un espacio después del título
            title.setSpacingAfter(20);
            // Añadimos el título al documento PDF
            document.add(title);

            // Creamos una tabla con 5 columnas para mostrar los datos
            PdfPTable table = new PdfPTable(5);
            // La tabla ocupará el 100% del ancho del documento
            table.setWidthPercentage(100);
            // Espaciado antes y después de la tabla
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Encabezados de las columnas
            String[] headers = { "ID", "Cédula", "Nombre", "Apellido", "Clave" };
            // Recorremos los encabezados para crear las celdas del header
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header));
                // Color de fondo gris claro para el encabezado
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                // Texto centrado horizontalmente
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                // Añadimos la celda a la tabla
                table.addCell(cell);
            }

            // Abrimos conexión a la base de datos usando la clase Conexion
            try (Connection con = Conexion.obtener();
                 // Preparamos la consulta SQL para obtener los usuarios
                 PreparedStatement pst = con.prepareStatement("SELECT id, cedula, nombre, apellido, clave FROM tblusuarios");
                 // Ejecutamos la consulta y obtenemos resultados
                 ResultSet rs = pst.executeQuery()) {
                
                // Recorremos cada fila del resultado
                while (rs.next()) {
                    // Añadimos cada dato a la tabla como una celda
                    table.addCell(String.valueOf(rs.getInt("id")));     // ID usuario
                    table.addCell(rs.getString("cedula"));               // Cédula
                    table.addCell(rs.getString("nombre"));               // Nombre
                    table.addCell(rs.getString("apellido"));             // Apellido
                    table.addCell(rs.getString("clave"));                // Clave
                }
            }

            // Añadimos la tabla con todos los datos al documento PDF
            document.add(table);
            // Cerramos el documento (muy importante para que se genere correctamente)
            document.close();

        } catch (Exception e) {
            // Si ocurre un error, lo registramos en el log con nivel SEVERE
            LOG.severe("❌ Error al generar el PDF: " + e.getMessage());
            // Lanzamos una excepción para que el servlet lo maneje y notifique el error
            throw new ServletException("Error al generar el PDF", e);
        }
    }
}
