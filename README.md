# WebDriver TestNG Project

This project is generated from an archetype. It exists out of TestNG, Selenium WebDriver, Allure and OWNER as the main dependencies.

## Compatibilidad
https://github.com/eclipse-aspectj/aspectj/blob/master/docs/release/JavaVersionCompatibility.adoc
Ahora mismo tienes AOP
|AspectJ version| 	Java version| 	Comments|
|1.9.22         |   22          | Cuidado con las versionde de java que vas a instalar!!! |


## Run tests
Clone the repo execute mvn install and click on the gree button or execute the command
`mvn clean test`
`mvn verify -Dcucumber.filter.tags="@test3"`
`mvn verify -X -Dcucumber.filter.tags="@test3"`

## Run tests in specific browser

`mvn clean test -Dbrowser=chrome`

`mvn clean test -Dbrowser=firefox`

`mvn clean test -Dbrowser=edge`

`mvn clean test -Dbrowser=chrome_headless`

`mvn clean test -Dbrowser=firefox_headless`

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