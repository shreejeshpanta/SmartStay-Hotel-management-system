Open this folder in IntelliJ as the Spring Boot backend.

Main file:
src/main/java/com/kairav/hotelapi/HotelApiApplication.java

Before running, update MySQL password in:
src/main/resources/application.properties

The backend auto-creates tables using JPA and seeds default admin/reception users plus sample rooms, customers, staff and services.

Proposal aligned smart features are in:
src/main/java/com/kairav/hotelapi/service/SmartAlgorithmService.java
src/main/java/com/kairav/hotelapi/controller/SmartController.java
