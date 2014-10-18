# swagger-liberty


This project shows how to define a Swagger JAX-RS REST API using IBM WebSphere Liberty Application Server. The server can also be pushed to IBM Bluemix PaaS as an optional step.

## Prerequisites

- Eclipse Luna
- [liberty profile in Eclipse] (https://developer.ibm.com/wasdev/downloads/liberty-profile-using-eclipse/)
- JDK 1.7
 
## Steps

- Import the project into Eclipse
- Create a new WebSphere ApplicationServer V8.5 Liberty profile
- Paste the server property file from deploy/server.xml to your Liberty profile
- Add the swagger-liberty application to the server
- Start the server
- Open a browser to http://localhost:9080/swagger-liberty/
- (Optional) push application to your IBM Bluemix account
 