# Authentication API (Keycloak + OAuth2.0 + Spring Security)
This project aims to present an authentication API using Keycloak tooling, OAuth and 
Spring Security. Authentication API is built in Maven.


## Keycloak Setup

In order to use this API, you will first need to install Keycloak locally in your machine, then you need to create the 
initial Admin user, and finally, the Master Realm (the starting point for the customized Realm, later on).
If you are using Ubuntu, you can follow: https://medium.com/@hasnat.saeed/setup-keycloak-server-on-ubuntu-18-04-ed8c7c79a2d9

As for customized Keycloak setup (after having a local server running, a first Admin user and Master Realm), you need to 
create several authentication related entities. A comprehensive example can be found in: https://www.baeldung.com/spring-boot-keycloak#create-realm

The Postman collection that already exists in the project's root has the following parametrization (in case you choose to keep it):

- **server**: http://localhost:8080
- **realm**: SpringBootKeycloak
- **client_id**: login-app
- **client_secret**: see Keycloak UI > Clients > login-app > Credentials
- **username**: see Keycloak UI > Users > View all users [¹]
- **password**: The one used when creating the user, or an updated one via Keycloak UI > Users > View all users > Credentials


### Notes
[¹] Both username and password are user dependant, so those need to be created initially (during basic setup).



## Install and Build
First install:
``` ./mvnw clean install``` 

Then, run:
``` ./mvnw spring-boot:run```



## Postman Integration

Two Postman Collections can be found: ```Keycloak mgmt```, using the environment
with the same name, to interact with Keycloak (generation of tokens, configuration, user information, etc); and a second one
```Autentication API```, using the environment with the same name, to interact with the endpoints of
interest for this API.

As a first step, both collections and environments need to be imported via Postman UI (documentation: https://learning.postman.com/docs/getting-started/importing-and-exporting-data/).

As preparation, please use _POST generation + refresh token_ on  ```Keycloak mgmt``` collection 
(with the matching environment) as the authentication step. Be aware that there needs to be a match between the user's credentials 
(created via Keycloak UI) and the body params of the said request. 

To interact with ```Autentication API```, change to the corresponding environment.

In case changes are done, please export the collection/env via UI and update it in project root. 
Keep v2.1 (the recommended one by Postman).
