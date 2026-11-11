# Test E2E — Catálogo Biblioteca UM (Selenium + TestNG)

Prueba de punta a punta con **Selenium WebDriver** sobre el catálogo en línea de
la biblioteca de la Universidad de Mendoza (Koha OPAC,
`https://biblioteca.um.edu.ar/`).

**Caso:** buscar *"Ingeniería de Software"* de Pressman y comprobar que existe.

## Stack

- **Selenium 4 (Java)** — incluye Selenium Manager (descarga el chromedriver solo)
- **TestNG** — framework de pruebas
- **Maven** — build y ejecución
- **Page Object Model** — patrón de diseño visto en clase

## Requisitos

- JDK 17 o superior
- Maven 3.8+
- Google Chrome instalado

## Cómo ejecutar

```bash
mvn clean test
```

La primera vez, Selenium Manager descarga el `chromedriver` adecuado. El
navegador se abre visible; para correrlo sin ventana:

```bash
HEADLESS=true mvn clean test
```

## Qué hace la prueba

1. Entra a `https://biblioteca.um.edu.ar/`.
2. Escribe *"Ingeniería de Software Pressman"* en el buscador y envía con Enter.
3. Comprueba que la búsqueda devolvió resultados y que algún título contiene
   *"software"*.

## Nota

El término incluye *"Pressman"* porque la búsqueda por palabra clave de Koha
indexa también el autor, así se apunta al libro concreto. Si la búsqueda no lo
encontrara, la prueba imprime los títulos devueltos (en la consola) para ajustar
el término o el texto esperado.

## Estructura

```
src/test/java/um/testing/
├── base/BaseTest.java            # ciclo de vida del WebDriver
├── pages/HomePage.java           # Page Object: catálogo + buscador
├── pages/SearchResultsPage.java  # Page Object: lista de resultados
└── tests/BuscarLibroTest.java    # la prueba
```
