package com.hitss.mercadolibre;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que ejecuta el flujo completo de búsqueda y extracción de información en MercadoLibre.
 */
public class EjercicioMercadolibre {

    /**
     * Método principal que configura el driver, realiza las acciones en MercadoLibre
     * y maneja excepciones.
     *
     * @param args 
     */
    public static void main(String[] args) {
        WebDriver driver = Navegador.configurarDriver(); 
        List<String> capturasPantalla = new ArrayList<>(); 

        try {
            
            MercadoLibreActions mercadoLibreActions = new MercadoLibreActions(driver, capturasPantalla);
            mercadoLibreActions.navegarPaginaPrincipal();
            mercadoLibreActions.realizarBusqueda("playstation 5"); 
            mercadoLibreActions.ordenarPorPrecio(); 
            mercadoLibreActions.mostrarDetallesProductos();
            
            //Genera el reporte despues de terminar de ejecutar las demas acciones
            ReporteGenerador.generarReporte(capturasPantalla);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit(); // Cierra el navegador al finalizar
        }
    }
}
