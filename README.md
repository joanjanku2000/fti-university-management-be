# FTI - University Management Project in Software Engineering 2

This project is given to us as an assignement in Software Engineering 2.

## Tech Stack
- Java 8
- Spring Boot 2
- Spring Security (JWT)

## Architecture

This application has a generic base which involves 
- Generic Controller Layer
- Generic Service Layer
- Generic Dao Layer
- Generic Converters , DTOs and Entities 
These features are in the form of BaseEntity,BaseService etc. The design uses
  interfaces and abstract classes.
  
The Generic Service Layer has 3 main components which are injected accordingly 

<T extends BaseDto , S extends BaseEntity>
- BaseDao<T,S>
- BaseConverter<T,S>
- BaseValidator<T,S>

The custom services are implemented in the implementation of the BaseService, whilst 
the BaseService supports all the CRUD operations. This base service is injected into a 
base controller which is implemented in specific ones. Again this base controller contains
the necessary endpoints for CRUD operations.

Security is implemented into a different module, almost indipendent of the rest.
