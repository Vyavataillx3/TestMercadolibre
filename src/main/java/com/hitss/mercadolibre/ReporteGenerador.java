package com.hitss.mercadolibre;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReporteGenerador {
    public static void generarReporte(List<String> capturasPantalla) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reporte.html"))) {
            writer.write("<html><head><title>Reporte de Ejecución</title></head><body>");
            writer.write("<h1>Reporte de Ejecución de MercadoLibre</h1>");
            writer.write("<p>Este reporte incluye las capturas de pantalla de cada paso ejecutado.</p>");
            
            for (String captura : capturasPantalla) {
                writer.write("<h2>" + captura + "</h2>");
                writer.write("<img src='capturas/" + captura + ".png' alt='" + captura + "' /><br>");
            }
            
            writer.write("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
