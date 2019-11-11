# Teaching-HEIGVD-AMT-2019-Project-One

> François Burgener, Tiago Povoa Q.

## In short

Our project is a web application where you can organize your future trips countries around the world. It runs on Payara. We'll see how to start it in the next chapter.

## Introduction

### Quick Start

In order to run this project, you'll need Docker and Maven.

We recommand to start by runing the following script in the root folder:

`$ ./start.sh`

It will do the following: build the project and start the containers.

Payara, MySql8, phpmyadmin.

And there you go. 

Once it has started, access it at the following address:

`http://{host_docker}:8080/countriestodo/`

### Overview

Let's pass quickly on what you can expect from this app. First you have an index page that invites you to sign up.

![firstpage](./pictures/firstpage.png)

Here you can register or directly sign in with one of our preset test users.

`username: toto, password: toto`

![signin](./pictures/signin.png)

And finally, the main page where you can plan your trips. Use the green button to add a new trip. You can delete or update the date and visited status as well. 

![main](./pictures/main.png)

The search and the page system is functional. You might as well want to change some account values, you can do it by clicking on the link with your name on it (top of the page, next to sign out).

## Under the hood

### Domain model

Let's deep dive into the domain model. 

## ![db](./pictures/db.jpg)

We have Users, Countries, Reasons (the motive you traveled for) and finally the trips who link everything together.

### Implementation

You can see below our Java package structure. 

![structure](./pictures/structure.png)

#### Packages

1. Business: security logic (bcrypt)
2. dao: the managing classes responsible of handling the requests to database
3. model: the representation of the data objects. 
4. web: the servlets that handle incoming requests and network aspects (forward, dispatch, ...)
5. dto: visitedCountryDTO is not an actual model, but the result of a more complex query. So we choose not to put it with models. 

## Tests

Our development environment uses a different URL:

`http://{docker_host}:8080/AMT-Projet/`

### Strategies

We used three testing strategies.

1. Unit testing with JUnit 5 and Mockito to mock the classes.
   1. We made dead simple tests for models
   2. Mockito allowed us to mock some parts in the Servlets.
2. Integration Testing with Arquillian
   1. Here we tested the managed objects in JavaEE.
   2. We had some difficulties to make it work with Payara since we had to connect in SSL using a certificate. 
3. Performance Testing with JMeter
   1. We tested the pagination

### Our tests

![tests](./pictures/tests.png)

If you want to run all our tests, you'll have to connect to Payara with a remote connection. It worked for us by removing the Arquillian plug-in from Intellij. 

```
URL: http://{docker_host}:8080/AMT-Projet/
PORT: 4848
Username: admin
Password: admin
( DEBUG_PORT: 9009 )
```

If you only want to run the unit tests, you don't need those extra steps.

### Performance



## What we achieved

trucs