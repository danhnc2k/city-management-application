
# City Mangement Application

A simple application to organize and manage data for a city, related to electricity, waste collection and water supply.


## Technologies

- city-management-miniapp:
    - Frontend UI built on ReactJs, NodeJs
- city-management-service:
    - Backend mircro-service built on Java, Spring boot

## Features

- Frontend:
  ![MainPageUI](https://github.com/user-attachments/assets/70ad6d45-94b0-4b98-9ae4-810bbef5d723)
  - UI to input data related to waste collection for a city
  ![AddCityUI](https://github.com/user-attachments/assets/6b8c2bb1-e65b-4a42-a29e-fe85236a8b98)
  - UI to display waste collection
  ![ViewWasteCollectorUI](https://github.com/user-attachments/assets/fc1946e6-7eb6-4420-8936-ab45ebb8ab4d)

- Backend:
  - Expose 2 APIs:
    - Create city data: /v1/city/create
    - Retrieve city data: /v1/city/retrieve
  - Store all data in Postgres database
- Testing:
  - All the Backend logic code were covered by Unit testing
  ![image](https://github.com/user-attachments/assets/bc0ca939-9a4e-4352-8723-e09edffba254)


## Architecture
### Frontend
For frontend, currently it's just a very simple UI to display some main functionality, with structure:
```
src
├───components        => common components that can be share between pages
├───pages             => contains all main pages of application 
├───router            => define routing of all pages
└───server            => integrate with microservice APIs
```
### Backend
#### Design Pattern

##### Hexagon Architecture

```
├─── app        => domain layer, contains main business logic
├─── domain     => domain layer, contains all business model definition
└─── infra      => infrastructure layer, contains all dependencies and input/output interaction
```
There some rules for this structure:
- All the business logic should be in ```app``` folder
- All the model related to business should be in ```domain``` folder
- All class in business layer will communicate through inteface and should not be depend on infrastructure layer. This can be achieve by apply **Facet design pattern**.

##### Facet Design Pattern
```
└───service
    ├───app
    │   └───facet
    │      ├───downstream
    |      │   └─── ExternalDataSource.java      => All classes in app and domain will interact with external service by this interface
    │      └───repository
    |          └─── CityRepository.java          => All classes in app and domain will interact with database by this interface
    └───infra
        ├───controller
        |   └─── CityController.java             => Expose API for input/output interaction
        ├───external
        │   └─── ExternalCityDataSourceImplementation.java   => Implement ExternalDataSource interface, define detail logic when interact with external service
        └───jpa
            └─── JpaCityRepository.java          => Implement CityRepository interface, define detail logic when interact with database
```
By using this pattern, the application becomes unaware of the nature of the things it's interacting with. If we need to changes in infratructure layer, it won't impact the domain layer.

##### Strategy Pattern

```
└─── app
    ├───service
    │   ├─── CityDataSourcingStrategy.java      => Strategy organizer class, hold and decide which strategy should be actioned
    │   ├─── CityService.java                   => Interface that expose common method, was implemented by InternalCityService and ExternalCityService
    │   ├─── ExternalCityService.java           => Sourcing city data from external source
    │   └─── InternalCityService.java           => Integrate with internal Database to retrieve/update city data
    ├───domain
```
This pattern will help to quickly adapt with new changes without refactoring our application. For example in the future we will need to aggregate data from other sources, we only need to create new class to implement ```CityService```.

#### Database Design
![image](https://github.com/user-attachments/assets/a4c5d757-9b09-45dc-8095-549f45613ea5)


## Future Enhancement

This project is now very simple, some of the features that can be enhance in the future:
### Frontend
  - Enhance UI/UX to support more complicated business flow
  - Consider to apply Backend For Frontend architecture to isolate interation between Frontend and Microservice
  - Update Unit test for all class
  - Consider set up Cypress testing for end to end flow
### Backend
  - Enhance database design to adapt with more complicated real life problem
  - Set up Docker to containerise the service for easily to run and deploy
  - Set up Blackbox testing using Gherkin and Cucumber

## Authors
- [@Danh Nguyen](https://github.com/danhnc2k)

## Reference
This project used some other resources to reference:
- React boilerplate template https://boilerplates.js.org/docs/react/
- ChatGPT suggestion related to business data and flow
