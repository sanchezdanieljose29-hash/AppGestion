package servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import controlador.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/ReporteClientespdf")
public class ReportePdf extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ReportePdf.class.getName());
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=ReporteClientes.pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Título
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Reporte de Clientes Registrados", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Tabla con 5 columnas
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            String[] headers = { "ID", "Cédula", "Nombre", "Apellido", "Clave" };
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // Consulta SQL
            try (Connection con = Conexion.obtener();
                 PreparedStatement pst = con.prepareStatement("SELECT id, cedula, nombre, apellido, clave FROM tblusuarios");
                 ResultSet rs = pst.executeQuery()) {
                
                while (rs.next()) {
                    table.addCell(String.valueOf(rs.getInt("id")));
                    table.addCell(rs.getString("cedula"));
                    table.addCell(rs.getString("nombre"));
                    table.addCell(rs.getString("apellido"));
                    table.addCell(rs.getString("clave"));
                }
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            LOG.severe("❌ Error al generar el PDF: " + e.getMessage());
            throw new ServletException("Error al generar el PDF", e);
        }
    }
}
