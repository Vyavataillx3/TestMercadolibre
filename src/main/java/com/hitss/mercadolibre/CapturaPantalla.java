package com.hitss.mercadolibre;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Esta clase proporciona métodos para capturas de pantalla usando el Selenium WebDriver
 * y almacenar las capturas en archivos.
 */
public class CapturaPantalla {

    private static final String RUTA_CAPTURAS = "capturas/";

    /**
     * Captura una pantalla del navegador controlado por Selenium WebDriver y la guarda como archivo.
     * 
     * @param driver              WebDriver que controla el navegador.
     * @param nombreArchivo       Nombre del archivo en el cual se guardará la captura.
     * @param capturasPantalla    Lista donde se agregarán las rutas de las capturas.
     */
    public static void capturarPantalla(WebDriver driver, String nombreArchivo, List<String> capturasPantalla) {
        validarCarpetaCapturas(RUTA_CAPTURAS);
        try {
            File archivoOrigen = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String rutaArchivo = RUTA_CAPTURAS + nombreArchivo + ".png";
            FileUtils.copyFile(archivoOrigen, new File(rutaArchivo));
            capturasPantalla.add(rutaArchivo);
        } catch (Exception e) {
            System.err.println("Error al capturar la pantalla: " + e.getMessage());
        }
    }

    /**
     * Verifica si la carpeta de capturas existe; si no, la crea.
     * 
     * @param rutaCarpeta Ruta de la carpeta a verificar o crear.
     */
    static void validarCarpetaCapturas(String rutaCarpeta) {
        File carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }
}
