package com.hitss.mercadolibre;

import org.openqa.selenium.WebDriver;


import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;



/**
 * Clase para configurar el web driver del navegador 
 */
public class Navegador {

    
    /**
     * Configura y retorna el Webdriver para el navegador "chrome"
     * @return   configurado para chrome
     */
    public static WebDriver configurarDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
