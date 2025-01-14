# WebDriver TestNG Project

This project is generated from an archetype. It exists out of TestNG, Selenium WebDriver, Allure and OWNER as the main dependencies.

## Compatibilidad

## Run tests
Clone the repo execute mvn install and click on the gree button or execute the command (En windows usar el comando de esta manera mvn verify -D cucumber.filter.tags="@test3 o mvn verify "-Dcucumber.filter.tags=@test3")    
`mvn clean test`  
`mvn clean verify`  
`mvn verify -Dcucumber.filter.tags="@test3"`  
`mvn verify -X -Dcucumber.filter.tags="@test3"`  

### Execute test using different browsers

`mvn clean verify -Dbrowser=firefox`

`mvn clean verify -Dbrowser=firefox_headless`

`mvn clean verify -Dbrowser=chrome`

`mvn clean verify -Dbrowser=chrome_headless`

`mvn clean verify -Dbrowser=edge`

### Execute only one feature
- Mac / Linux  
  `mvn verify -Dcucumber.filter.tags="@ResellerOperatorAddKits2KitsforDevelopment"`
- Windows  
  `mvn verify -D cucumber.filter.tags="@ResellerOperatorAddKits2KitsforDevelopment"`

### Execute only one scenario
- Mac / Linux  
  `mvn verify -Dcucumber.filter.tags="@ResellerOperatorAddKits2KitsforDevelopment"`
- Windows  
  `mvn verify -D cucumber.filter.tags="@ResellerOperatorAddKits2KitsforDevelopment"`

## Generate report

`mvn allure:report`

## Serve report

`mvn allure:serve`

## Check for dependency updates

`mvn versions:display-dependency-updates`

## Update to the latest release version

`versions:update-properties`

----------
## Links
Wiki project -> [HERE](https://github.com/rommelayala/template_selenium_java/wiki)

Base project www.testsmith.io [HERE ](https://www.linkedin.com/pulse/selenium-webdriver-testng-maven-archetypes-testsmith/)
````
mvn archetype:generate -B -DarchetypeGroupId=io.testsmith \
                       -DarchetypeArtifactId=webdriver-testng-archetype \
                       -DarchetypeVersion=1.4.21.0 \
                       -DgroupId=com.example \
                       -DartifactId=my-new-project \
                       -Dversion=0.1-SNAPSHOT
````

Webdriver Event Listeners [HERE](https://github.com/testsmith-io/webdriver-event-listeners)

Webdriver Manager [DOCS](https://bonigarcia.dev/webdrivermanager/#webdrivermanager-and-selenium-manager), [GITHUB](https://github.com/bonigarcia/webdrivermanager)

Owner Docs [HERE](https://matteobaccan.github.io/owner/docs/welcome/)