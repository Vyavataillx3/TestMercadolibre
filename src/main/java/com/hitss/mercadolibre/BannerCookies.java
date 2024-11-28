package com.hitss.mercadolibre;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Clase para manejar el cierre del banner de cookies en MercadoLibre.
 */
public class BannerCookies {

    /**
     * Método para intentar cerrar el banner de cookies en la página.
     *
     * @param driver el WebDriver de Selenium
     */
    public static void cerrarBanner(WebDriver driver) {
        try {
            int intentos = 0; // Contador de intentos para cerrar el banner
            boolean bannerCerrado = false; // Estado del banner

            while (intentos < 3 && !bannerCerrado) {
                try {
                    // Espera a que el botón de cierre del banner sea clickable
                    WebElement closeButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                            .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cookie-consent-banner__button")));
                    closeButton.click(); // Intenta cerrar el banner
                    bannerCerrado = true; // Actualiza el estado si se cierra correctamente
                } catch (Exception e) {
                    System.out.println("No se encontró el banner o no se pudo cerrar, reintentando...");
                }
                Thread.sleep(100); // Espera 100 milisegundos antes de reintentar
                intentos++;
            }

            if (!bannerCerrado) {
                System.out.println("No se pudo cerrar el banner de cookies después de varios intentos.");
            }

        } catch (Exception e) {
            System.out.println("Error al intentar cerrar el banner de cookies.");
        }
    }
}
