[![Build Status](https://travis-ci.org/superernie77/currencyconverter.svg?branch=master)](https://travis-ci.org/superernie77/currencyconverter)
[![volkswagen status](https://auchenberg.github.io/volkswagen/volkswargen_ci.svg?v=1)](https://github.com/auchenberg/volkswagen)
[![Coverage Status](https://coveralls.io/repos/github/superernie77/currencyconverter/badge.svg?branch=master)](https://coveralls.io/github/superernie77/currencyconverter?branch=master)

# Currency Converter
A webapp to convert your riches into another currency of your choice!

# How to build and start the app
You will need to have Maven and Java 8 installed on your machine. Check out the source code and run the following Maven command on your command line to build an executable jar :
```
  mvn clean verify
```
This will create an executable jar file in the target directory of your checkout folder.
You can start the application with the following command:
```
  java -jar currencyConverter-0.0.1-SNAPSHOT.jar
```

# Used Technologies
The following technologies were used to build the web application:
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data-JPA
- Thymeleaf
- Jackson
- Maven
- Junit / Mockito
- H2 DB

The following technologies were used for the build pipeline:
- GitHub
- Travis CI
- Heroku
- Coveralls / Jacoco
- Slack ( currency-converter.slack.com )

