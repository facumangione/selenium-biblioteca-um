package um.testing.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import um.testing.base.BaseTest;
import um.testing.pages.HomePage;
import um.testing.pages.SearchResultsPage;

/**
 * Prueba E2E con Selenium sobre el catalogo de la biblioteca de la UM.
 *
 * Caso: buscar "Ingenieria de Software" de Pressman y comprobar que existe.
 */
public class BuscarLibroTest extends BaseTest {

    // Se incluye "Pressman" para apuntar al libro concreto: la busqueda de
    // palabra clave de Koha indexa tambien el autor.
    private static final String TERMINO = "Ingeniería de Software Pressman";
    private static final String TEXTO_ESPERADO = "software"; // palabra presente en el titulo

    @Test(description = "Busca 'Ingenieria de Software' de Pressman y verifica que exista")
    public void buscarLibroIngenieriaDeSoftware() {
        // 1) Ingresar al catalogo
        HomePage home = new HomePage(driver).open();

        // 2) Buscar el libro
        SearchResultsPage resultados = home.buscar(TERMINO);

        // 3) Diagnostico (se imprime antes de las aserciones)
        int cantidad = resultados.cantidadResultados();
        System.out.println("URL de resultados : " + driver.getCurrentUrl());
        System.out.println("Cantidad          : " + cantidad);
        System.out.println("Titulos devueltos : " + resultados.titulos());

        // 4) Comprobaciones
        Assert.assertTrue(cantidad > 0,
                "La busqueda de '" + TERMINO + "' no devolvio ningun resultado");

        Assert.assertTrue(resultados.contieneLibro(TEXTO_ESPERADO),
                "Ningun titulo contiene '" + TEXTO_ESPERADO + "'. Titulos: " + resultados.titulos());

        System.out.println("OK - El libro de Pressman existe en el catalogo.");
    }
}
