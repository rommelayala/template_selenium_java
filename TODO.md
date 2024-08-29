# TODO:

[ ] Refactoriza metodos para tener métodos genéricos de encontrar web elements  
[ ] Generar un reporte en allure por cada ejecucion que tenga por nombre dd-mm-aaaa HH:MM:SS  
[ ] Corregir porque no se muestran mensajes del logger por consola
[ ] Dockerizar los tests
[ ] Crear steps genericos para web
[ ] Crear steps genericos para rest
[ ] Crear steps genericos para db

--------------- In progess ---------------
-[ ] Integrar Cucumber

--------------- Features ---------------

✅ Usar linea de comandos para ejecutar archivo properties de un environment  
Los archivos properties en src/main/resources/{test}_environment.properties se modelan con estas clases clases [Environment.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fproperties%2FEnvironment.java) (src/main/java/com/example/properties/Environment.java) y [EnvironmentProperties.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fproperties%2FEnvironmentProperties.java), para poder usarlo   
1 Crea tu archivo properties [test_environment.properties](src%2Fmain%2Fresources%2Ftest_environment.properties)
````
url=https://practicesoftwaretesting.com
username=username
password=password
--> toto=tata
````
2 Enlaza el properties con la interfaz [EnvironmentProperties.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fproperties%2FEnvironmentProperties.java), esta interfaz ya es generica {env} y lo toma desde la comando mvn test -Denv=test solo debes de declarar los nuevos atributos/parametros que quieres rescatar
````
@Sources("file:src/main/resources/${env}_environment.properties" )
public interface EnvironmentProperties extends Config {
    String url();

    String username();

    String password();

--> String toto();
}
````
3 Utiliza tu atributo desde el @Step
````
package com.example.pages;

public class HomePage extends AbstractPageBase {

  private final By signInButton = By.cssSelector("li>a[data-test='nav-sign-in']");

  @Step("Open the Practice Software Testing homepage")
  public HomePage open() {
    driver.get(Environment.getProperties().url());
--> //Mostrar valores de un atributo 
--> //System.out.println("EL valor del nuevo atributo es -> " + Environment.getProperties().toto());
    return this;
  }
````
lanza tus test 
````
mvn clean test -Denv=test
````

-------
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