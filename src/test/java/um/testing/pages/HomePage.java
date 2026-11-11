package um.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object de la pagina principal del catalogo (Koha OPAC) de la UM.
 *
 * El patron Page Object Model (visto en clase) mantiene los selectores en esta
 * clase; la prueba solo llama metodos de negocio (open, buscar).
 */
public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String URL = "https://biblioteca.um.edu.ar/";

    // En Koha el campo de palabra clave siempre se llama "q" (parametro GET que
    // viaja a opac-search.pl), por eso el selector es estable entre versiones.
    private final By campoBusqueda = By.cssSelector("input[name='q']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /** Abre el catalogo y espera a que el buscador este visible. */
    public HomePage open() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(campoBusqueda));
        return this;
    }

    /**
     * Escribe el termino y envia con Enter (no dependemos del selector del boton).
     * Devuelve el Page Object de resultados.
     */
    public SearchResultsPage buscar(String termino) {
        WebElement input = driver.findElement(campoBusqueda);
        input.clear();
        input.sendKeys(termino);
        input.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}
