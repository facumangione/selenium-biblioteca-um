package um.testing.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Clase base de las pruebas Selenium.
 *
 * Centraliza el ciclo de vida del WebDriver (abrir y cerrar el navegador).
 * Selenium 4 trae Selenium Manager: descarga el chromedriver que corresponde a
 * la version de Chrome instalada, asi que no hay que gestionarlo a mano.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    /** Se ejecuta antes de CADA @Test: un navegador limpio por prueba. */
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // HEADLESS=true corre sin ventana (util en CI). Por defecto, visible.
        if (Boolean.parseBoolean(System.getenv().getOrDefault("HEADLESS", "false"))) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);
    }

    /** Se ejecuta despues de CADA @Test, aunque falle: cierra el navegador. */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
