# TODO:

[ ] Corregir la creacion de reportes de cucumber
[ ] Corregir porque no se muestran mensajes del logger por consola
[ ] Dockerizar los tests
[ ] Crear steps genericos para rest
[ ] Crear steps genericos para db
[ ] Añadir try catch

--------------------------------------------- In progess -------------------------------------------------------

--------------------------------------------- Features ---------------------------------------------------------
----------------------------------------------------------------------------------------------------------------
✅ Configuracion de logs con logback https://javatechonline.com/logback-xml-configuration-examples/
incluir en el pom

<dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>${logback-classic.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>commons-logging</groupId>
                    <artifactId>commons-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
y añadir las exclusiones 
       <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium-java.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>io.opentelemetry</groupId>
                    <artifactId>*</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
Las exclusiones para solucionar el problema de los conflictos entre dependencias, el puente de java.util.loggin a slf4j 

<dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jul-to-slf4j</artifactId>
            <version>2.0.9</version>
        </dependency>

Configura el puente

````
public class EnvironmentProperties implements Config {

/** Get properties and set them as system properties */
public static void initializeProperties() {
// Desactiva los handlers predeterminados de JUL
LogManager.getLogManager().reset();

    // Instala el puente de SLF4J
    SLF4JBridgeHandler.install();

    // Prueba el redireccionamiento
    Logger julLogger = Logger.getLogger(EnvironmentProperties.class.getName());
    julLogger.info("Este es un mensaje de java.util.logging redirigido a Logback.");

    org.slf4j.Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger(EnvironmentProperties.class);
    slf4jLogger.info("Este es un mensaje de SLF4J.");

````
verificar conflictos entre dependencias 
mvn dependency:tree | grep -i log

```
[INFO] |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.38.0:compile
[INFO] |  |  |  \- io.opentelemetry:opentelemetry-sdk-logs:jar:1.38.0:compile
[INFO] +- ch.qos.logback:logback-classic:jar:1.5.6:compile
[INFO] |  +- ch.qos.logback:logback-core:jar:1.5.6:compile
[INFO] +- org.apache.logging.log4j:log4j-slf4j-impl:jar:3.0.0-beta1:compile
[INFO] |  +- org.apache.logging.log4j:log4j-api:jar:3.0.0-beta1:compile
[INFO] |  \- org.apache.logging.log4j:log4j-core:jar:3.0.0-beta1:runtime
[INFO] |     \- org.apache.logging.log4j:log4j-plugins:jar:3.0.0-beta1:runtime
[INFO] |  \- commons-logging:commons-logging:jar:1.2:compile
```
Cómo interpretar los resultados
1. Formato de las dependencias

   Cada línea representa una dependencia del proyecto o una dependencia transitiva (es decir, una librería que fue traída automáticamente por otra librería).
   La jerarquía está indicada por el nivel de indentación (| | | +-).
2. Ejemplo:
````
[INFO] +- ch.qos.logback:logback-classic:jar:1.5.6:compile

````

Esto significa que logback-classic es una dependencia directa.
````
[INFO] |  +- ch.qos.logback:logback-core:jar:1.5.6:compile
````
Esto indica que logback-core es una dependencia transitiva de logback-classic.

2. Componentes clave de cada dependencia

Cada línea sigue este formato:
````
[groupId]:[artifactId]:[type]:[version]:[scope]

    groupId: Identifica el grupo o proveedor de la librería. Ejemplo:
        ch.qos.logback es el grupo para Logback.
        org.apache.logging.log4j es el grupo para Log4j.

    artifactId: Es el nombre específico de la librería. Ejemplo:
        logback-classic y logback-core son componentes de Logback.
        log4j-api y log4j-core son componentes de Log4j.

    type: Generalmente es jar (archivo Java).

    version: La versión específica de la librería.

    scope: Indica cómo se usa la dependencia:
        compile: Se requiere en tiempo de compilación.
        runtime: Solo se usa en tiempo de ejecución.

````

Para usar los loggers hay que hacerlo de esta manera 

````
Example 1 -> haciendo puente de java.util.logger a slf4j 
 public class EnvironmentProperties implements Config {

  /** Get properties and set them as system properties */
  public static void initializeProperties() {
    // Desactiva los handlers predeterminados de JUL
    LogManager.getLogManager().reset();

    // Instala el puente de SLF4J
    SLF4JBridgeHandler.install();

    // Prueba el redireccionamiento
    Logger julLogger = Logger.getLogger(EnvironmentProperties.class.getName());
    julLogger.info("Este es un mensaje de java.util.logging redirigido a Logback.");

    org.slf4j.Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger(EnvironmentProperties.class);
    slf4jLogger.info("Este es un mensaje de SLF4J.");

-----------------------
Example 2
public class RommonRestUtil {
    private RequestSpecification RestRequest;
    private io.restassured.response.Response RestResponse;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(RommonRestUtil.class);
    /**
    
````
Para controlar la salida de los logs crear un archivo logbak.xml en src/main/resources

````
<configuration>
    <!-- Definir un appender para la consola -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    <!-- Este es la configuracion global de los mensajes
     Prioridad:
    TRACE (más detallado, menor prioridad)
    DEBUG
    INFO
    WARN
    ERROR (menos detallado, mayor prioridad).
    -->
    <!-- Ignorar este error -->
    <root level="TRACE">
        <appender-ref ref="CONSOLE"/>
    </root>
        <!-- Logger específico para Selenium WebDriver Event Listeners pesa mas que la configuracion global-->
    <logger name="io.testsmith" level="INFO" additivity="true">
        <appender-ref ref="CONSOLE" />
    </logger>
</configuration>
````  
Un appender en Logback es un componente encargado de enviar los mensajes de registro (logs) a un destino específico, como la consola, un archivo, una base de datos, o incluso un servidor remoto. En el ejemplo que te proporcioné:  
control global del nivel de salida de los logs 
````
<root level="TRACE">
        <appender-ref ref="CONSOLE"/>
    </root>
````
Si hay un logger con diferente level este level manda



----------------------------------------------------------------------------------------------------------------

He Reparado save the file en el metodo onError creando estas nuevas clases:  
[SavepageSourceOnExceptionListener.java](src%2Ftest%2Fjava%2Fcom%2Fexample%2Futils%2FSavepageSourceOnExceptionListener.java)  
[SavescreenshotOnExceptionListener.java](src%2Ftest%2Fjava%2Fcom%2Fexample%2Futils%2FSavescreenshotOnExceptionListener.java)  
----------------------------------------------------------------------------------------------------------------
Cargar los properties como variables de sistema en Hooks.java - loadProperties() cargo las variables 
solucionando el que no se pueda ejecutar multiples features uno tras de otro     

para obtenerlas usar
`System.getProperty("url-pp")

----------------------------------------------------------------------------------------------------------------
✅ Generar un reporte en allure por cada ejecucion que tenga por nombre dd-mm-aaaa HH:MM:SS  
Preconditions
- Debe existir la carpeta target/allure-results
  si no existe ejecuta los test
  ````shell
  mvn test -Dbrowser=firefox_headless -Denv=test
  ````
- Debe existir steps validos src/test/resources/features/login.feature

Ejecucion  

1.- Luego de que exista target/allure-results, vamos a usar este comando   
  ````shell
  mvn allure:report -Dallure.results.directory=target/allure-results -Dallure.report.directory=target/site/allure-maven-plugin
  ````
  Detalles del comando:

  mvn allure:report: Ejecuta la meta report del plugin de Allure, encargada de generar el reporte.  
  -Dallure.results.directory=target/allure-results: Especifica el directorio donde se encuentran los resultados de las pruebas. Si no se especifica, el plugin buscará en el directorio predeterminado (target/allure-results).  
  -Dallure.report.directory=target/site/allure-maven-plugin: Especifica el directorio de salida para el reporte generado. Si no se especifica, el reporte se generará en el directorio predeterminado (target/site/allure-maven-plugin).  
  Para nuestro caso  
  ````shell
  mvn allure:report
  ````  
2.- Desde el directorio target ejecutar a veces vas a necesitar el --clean "allure generate --clean allure-results" 
  ````shell
  allure generate allure-results
  ````  
3.- Copia el contenido de target/allure-report/history que previamente se ha generado con el comando anterior a la carpeta target/allure-results, sino existe /history copia la carpeta completa

4.- Levanta el allure server
  ````shell
    mvn allure:serve
  ````  

----------------------------------------------------------------------------------------------------------------
✅ Corregir la generacion de reports
No se estaban generando los reportes correctamente  
Para eso actualicé
en el pom
````
<properties>
    <allure-testng.version>2.27.0</allure-testng.version> <-- version anterior 2.27.0
</properties>
        .
        .
        .
 <dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-cucumber7-jvm</artifactId> <---Actualice la antigua version <artifactId>allure-cucumber5-jvm</artifactId>
    <version>2.21.0</version>
</dependency>
````
en el RunCucumberTest.java añadí mas plugins
````
 plugin = {
      "pretty",
      "html:target/cucumber-reports.html",
      "json:target/cucumber-reports/cucumber.json",
      "rerun:target/cucumber-reports/rerun.txt",
      "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" <--
    })
````
OJO: 
- Antes de Ejecutar el server, primero ejecuta los test
- Para ver los test en el reporte de Allure, primero ejecuta 
  ````
  - mvn allure:report
  ````
  y luego de compilar el reporte levanta el servidor 
  ````
  - mvn allure:serve
  ````
  ### Links
- https://allurereport.org/docs/testng-reference/

----------------------------------------------------------------------------------------------------------------
✅ Corrige el default search engine en chrome
-> chromeOptions.addArguments("-disable-search-engine-choice-screen");
chromeOptions.addArguments("--no-default-browser-check");
chromeOptions.addArguments("--disable-infobars");
chromeOptions.addArguments("--disable-popup-blocking");
chromeOptions.addArguments("--disable-notifications");
----------------------------------------------------------------------------------------------------------------
✅ Refactoriza metodos para tener métodos genéricos de encontrar web elements
----------------------------------------------------------------------------------------------------------------
✅ Integrar Cucumber  
TENER EN CUENTA QUE ESTAMOS INTEGRANDO CUCUMBER-TESTNG  
Para integrar cucumber:
1 añadir dependencias al POM
````xml
<project>
    <!-- Cucumber core -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-core</artifactId>
        <version>7.14.0</version>
    </dependency>
    <!-- Cucumber Java -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.14.0</version>
    </dependency>
    <!-- Cucumber TestNG -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-testng</artifactId>
        <version>7.14.0</version>
    </dependency>
    <!-- Cucumber Gherkin Parser -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>gherkin</artifactId>
        <version>26.0.0</version>
    </dependency>
    <!-- Cucumber Allure -->
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-cucumber5-jvm</artifactId>
        <version>2.17.3</version>
    </dependency>
</project>
````
2 Crear Carpetas:
Para el código  
- src/test/java/com/example/runners  
- src/test/java/com/example/stepDefinitions  
Para los features  
- src/test/resources/features

2.1 El Runner
````java
package com.example.runners;

@CucumberOptions(
        features = "src/test/resources/features", //<-- indica el lugar de los features
        glue = "com.example.stepDefinitions",  //<-- indica el lugar de los stepdefinitions
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {}
````
2.2 El Hooks, es Super Importante porque van a ejecutarse metodos para instanciar el webdriver por ejemplo 
````java
package com.example.stepDefinitions;

public class Hooks {
    @Before
    public void beforeScenario() {
        TestBase.setup(); // Inicializa el WebDriver
    }

    @After
    public void afterScenario() {
        TestBase.teardown(); // Cierra el WebDriver
    }
}
````
2.3 El archivo de steps 
```java
package com.example.stepDefinitions;

public class LoginSteps {
  private WebDriver driver = TestBase.getDriver();
  private final HomePage homePage = new HomePage(driver);
  @Given("I am on the login page")
  public void iAmOnTheLoginPage() {

    homePage.open();
  }
}
```
2.4 El features
````gherkin
Feature: Login Functionality

  Scenario: Successful login with valid credentials
    Given I am on the login page

````
----------------------------------------------------------------------------------------------------------------
✅ Usar linea de comandos para ejecutar archivo properties de un environment  
Los archivos properties en src/main/resources/{test}_environment.properties se modelan con estas clases clases [Environment.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fproperties%2FEnvironment.java) (src/main/java/com/example/properties/Environment.java) y [EnvironmentProperties.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fproperties%2FEnvironmentProperties.java), para poder usarlo   
1 Crea tu archivo properties [test_environment.properties](src%2Fmain%2Fresources%2Ftest_environment.properties)

````properties
url=https://practicesoftwaretesting.com
username=username
password=password
-->=toto=tata
````
2 Enlaza el properties con la interfaz [EnvironmentProperties.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fproperties%2FEnvironmentProperties.java), esta interfaz ya es generica {env} y lo toma desde la comando mvn test -Denv=test solo debes de declarar los nuevos atributos/parametros que quieres rescatar
````java
@Sources("file:src/main/resources/${env}_environment.properties" )
public interface EnvironmentProperties extends Config {
    String url();

    String username();

    String password();

// --> String toto();
}
````
3 Utiliza tu atributo desde el @Step
````java
package com.example.pages;

public class HomePage extends AbstractPageBase {

  private final By signInButton = By.cssSelector("li>a[data-test='nav-sign-in']");

  @Step("Open the Practice Software Testing homepage")
  public HomePage open() {
    driver.get(Environment.getProperties().url());
// --> //Mostrar valores de un atributo 
//--> //System.out.println("EL valor del nuevo atributo es -> " + Environment.getProperties().toto());
    return this;
  }
}
````
lanza tus test 
````shell
mvn clean test -Denv=test
````

----------------------------------------------------------------------------------------------------------------
✅ Usar linea de comandos para levantar el browser
+ Es posible usar diferentes browsers ya definidos en esta clase src/test/java/com/example/utils/Browser.java.
````
CHROME, FIREFOX, EDGE, CHROME_HEADLESS, FIREFOX_HEADLESS
````
Para la creación de los drivers se usa esta clase src/test/java/com/example/utils/BrowserUtil.java  
Para ejecutar los test usando el browser usando maven
````
mvn clean test -Dbrowser=firefox_headless
````
