package com.hitss.mercadolibre;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.ArrayList;
import java.time.Duration;

/**
 * Clase MercadoLibreActions que encapsula las acciones principales que se pueden realizar en MercadoLibre.
 */
public class MercadoLibreActions {

    private WebDriver driver;
    private List<String> capturasPantalla;

    /**
     * Constructor para inicializar el driver de Selenium y la lista de capturas de pantalla.
     *
     * @param driver el WebDriver de Selenium
     * @param capturasPantalla la lista para almacenar rutas de las capturas de pantalla
     */
    public MercadoLibreActions(WebDriver driver, List<String> capturasPantalla) {
        this.driver = driver;
        this.capturasPantalla = capturasPantalla;
    }

    /**
     * Navega a la página principal de MercadoLibre y selecciona el país México.
     */
    public void navegarPaginaPrincipal() {
        driver.get("https://www.mercadolibre.com/");
        driver.manage().window().maximize();
        CapturaPantalla.capturarPantalla(driver, "seleccion_pais_mexico", capturasPantalla);

        WebElement mexicoLink = esperarElemento(driver, By.linkText("México"));
        mexicoLink.click();
        esperar(2000);
        BannerCookies.cerrarBanner(driver);
    }

    /**
     * Realiza una búsqueda en MercadoLibre.
     *
     * @param busqueda el término de búsqueda
     */
    public void realizarBusqueda(String busqueda) {
        WebElement searchBox = esperarElemento(driver, By.name("as_word"));
        searchBox.sendKeys(busqueda);
        searchBox.submit();
        esperar(2000);
        CapturaPantalla.capturarPantalla(driver, "Busqueda_play_5", capturasPantalla);
    }

    /**
     * Ordena los resultados de búsqueda por precio de mayor a menor.
     */
    public void ordenarPorPrecio() {
        WebElement ordenarPorButton = esperarElemento(driver, By.cssSelector(".andes-dropdown__trigger"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", ordenarPorButton);
        esperar(1000);

        WebElement sortPriceDesc = esperarElemento(driver, By.xpath("//span[contains(text(), 'Mayor precio')]"));
        js.executeScript("arguments[0].scrollIntoView(true);", sortPriceDesc);
        js.executeScript("arguments[0].click();", sortPriceDesc);
        esperar(2000);
        CapturaPantalla.capturarPantalla(driver, "filtrar_orden_mayor_a_menor", capturasPantalla);
    }

    /**
     * Muestra los detalles de los primeros 5 productos de la búsqueda.
     */
    public void mostrarDetallesProductos() {
        List<WebElement> products = driver.findElements(By.cssSelector(".ui-search-layout__item"));
        List<String> productDetails = new ArrayList<>();
        for (int i = 0; i < Math.min(products.size(), 5); i++) {
            WebElement product = products.get(i);

            // selector para la marca del producto
            WebElement productBrand = esperarElemento(driver, By.cssSelector(".poly-component__brand"));
            String brand = productBrand.getText();

            // selector para el título del producto
            WebElement productTitle = esperarElemento(driver, By.cssSelector(".poly-component__title a"));
            String name = productTitle.getText();

            // selector para el precio del producto
            WebElement productPrice = esperarElemento(driver, By.cssSelector(".andes-money-amount--cents-superscript"));
            String price = productPrice.getText().replaceAll("\\n", "");

            productDetails.add("Marca: " + brand + " - Producto: " + name + " - Precio: $" + price);
        }

        productDetails.forEach(System.out::println);
    }

    /**
     * Espera a que un elemento esté visible en la página.
     *
     * @param driver el WebDriver de Selenium
     * @param locator el By locator del elemento
     * @return el WebElement localizado
     */
    private static WebElement esperarElemento(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Selenium 4.x
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Espera un tiempo determinado (en milisegundos).
     *
     * @param tiempo el tiempo a esperar en milisegundos
     */
    private static void esperar(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
