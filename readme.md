# MicroProfile Standalone examples

This project contains some examples of the standalone MicroProfile specifications, 
which are used in my blog about the introduction to the standalone MicroProfile specifications at the [Craftsmen website](https://craftsmen/nl)

It contains sample code for the following MicroProfile specifications:
- Reactive Streams Operators
- Reactive Messaging
- Context Propagation and
- GraphQL

## Running the application

Using Maven you can run the application on Quarkus using the following statement

```commandline
mvn quarkus:dev
```

This application runs on http://localhost:8080 and contains the following endpoints:

- http://localhost:8080/hello
- http://localhost:8080/drivers

# MicroProfile generated Application

## Introduction

MicroProfile Starter has generated this MicroProfile application for you.

The generation of the executable jar file can be performed by issuing the following command

    mvn clean package

This will create an executable jar file **standalone-microbundle.jar** within the _target_ maven folder. This can be started by executing the following command

    java -jar target/standalone-microbundle.jar

To launch the test page, open your browser at the following URL

    http://localhost:8080/index.html

## Specification examples

By default, there is always the creation of a JAX-RS application class to define the path on which the JAX-RS endpoints are available.

Also, a simple Hello world endpoint is created, have a look at the class **HelloController**.

More information on MicroProfile can be found [here](https://microprofile.io/)
