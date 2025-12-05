package com.biblioteca.service.autor;

import com.biblioteca.dto.AutorDto;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AutorPdfService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public byte[] generarReporteAutores(List<AutorDto> autores) throws DocumentException {

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();

        // Encabezado

        Font tituloFont = new Font(Font.HELVETICA, 18, Font.BOLD, Color.blue);

        Paragraph titulo = new Paragraph("Reporte de Autores", tituloFont);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);

        // Tabla

        PdfPTable table = new PdfPTable(5); // 5 columnas
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);

        // Anchos de columna
        table.setWidths(new float[] {1.2f, 2.5f, 2.5f, 2.5f, 2f});

        // Encabezados
        agregarCeldaEncabezado(table, "ID");
        agregarCeldaEncabezado(table, "Nombre");
        agregarCeldaEncabezado(table, "Apellido");
        agregarCeldaEncabezado(table, "Nacionalidad");
        agregarCeldaEncabezado(table, "Nacimiento");

        // Contenido dinamico
        for (AutorDto autor : autores) {
            table.addCell(String.valueOf(autor.autorId()));
            table.addCell(autor.nombre());
            table.addCell(autor.apellido());
            table.addCell(autor.nacionalidad());

            // Fecha formateada o vacío si es null
            String fecha = autor.fechaNacimiento() != null
                    ? autor.fechaNacimiento().format(DATE_TIME_FORMATTER)
                    : "—";

            table.addCell(fecha);
        }

        document.add(table);
        document.close();

        return out.toByteArray();
    }

    private void agregarCeldaEncabezado(PdfPTable table, String texto){
        Font headerFont = new Font(Font.HELVETICA, 12, java.awt.Font.BOLD);
        PdfPCell cell = new PdfPCell(new Phrase(texto, headerFont));
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(5);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.addCell(cell);
    }
}
