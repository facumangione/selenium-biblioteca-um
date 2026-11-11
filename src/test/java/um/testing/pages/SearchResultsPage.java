package um.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object de la pagina de resultados del catalogo Koha OPAC.
 *
 * En Koha cada titulo de la lista de resultados es un enlace con clase "title".
 */
public class SearchResultsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By titulosLocator = By.cssSelector("a.title");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /** Cantidad de resultados. Si no hay, el wait expira y devolvemos 0. */
    public int cantidadResultados() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(titulosLocator));
        } catch (TimeoutException e) {
            return 0;
        }
        return driver.findElements(titulosLocator).size();
    }

    /** Textos de todos los titulos devueltos (para diagnostico/evidencia). */
    public List<String> titulos() {
        return driver.findElements(titulosLocator).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /** true si algun titulo contiene el texto (sin distinguir mayusculas). */
    public boolean contieneLibro(String texto) {
        String objetivo = texto.toLowerCase();
        return titulos().stream()
                .anyMatch(t -> t.toLowerCase().contains(objetivo));
    }
}
