Gas Station backend Applicant Service

# Task Description
You should be able to start the example application by executing net.bigpoint.gasstation.GasstationServerApplicant, which starts a webserver on port 8080 (http://localhost:8080) and serves SwaggerUI where can inspect and try existing endpoints.

The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring MVC with Spring Boot
* Database H2 (In-Memory)
* Maven
* Intellij as IDE is preferred but not mandatory. We do provide code formatter for intellij and eclipse in the etc folder.


The goal is to implement the interface of a gas station which has multiple gas pumps. 
Each gas pump provides a specific amount of gas of a certain gas type.
Additionally, customers are requesting gas with different requirements regarding the
amount, type of gas and price they are willing to pay for it. Your implementation has to
take all this into account and serve gas when available. 
For further information please refer to the documentation of our classes.
