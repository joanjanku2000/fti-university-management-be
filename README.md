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
- BaseConverter<T,S> -> performs conversions
- BaseValidator<T,S> -> contains void methods where exceptions are thrown in case of not valid inputs

The custom services are implemented in the implementation of the BaseService, whilst 
the BaseService supports all the CRUD operations. This base service is injected into a 
base controller which is implemented in specific ones. Again this base controller contains
the necessary endpoints for CRUD operations.

Security is implemented into a different module, almost indipendent of the rest.

## Technical Guide 

### Creating a new Rest API

- Create a New Package under uniman in API and Core packages respectivly with the same names
- In core/{newPackage} add these necessary packages: entity,dto,converter,dao,service,validator
- Create the entity extending BaseEntity
- Create the dto extening BaseDto
- Create the dao extending BaseDao with the necessary params (createdDto,CreatedEntity)
- Create the converter implementing BaseConverter with necessary params (createdDto,CreatedEntity)
- Create the validator implementing BaseValidator with the necessary params (createdDto,CreatedEntity)
- Create the service extending BaseServiceAbstractImpl with the necessary parameters (createdDto,CreatedEntity)

- Create the RestController extending BaseController with the necessary params (createdDto,CreatedEntity) and the custom @RequestMapping("/path")

_**Note , the base components contain only CRUDs, for custom services , methods should be implemented in the created components
just like the already implemented services in the System**_