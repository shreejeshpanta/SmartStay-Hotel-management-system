SMART STAY - Hotel management system

 It includes:

1. Spring Boot backend
2. JavaFX desktop frontend
3. MySQL database script
4. Login with BCrypt encrypted password storage
5. Room management
6. Customer/guest management
7. Reservation and booking management
8. Real-time availability conflict checking to prevent double booking
9. Check-in and check-out
10. Automated billing/payment records
11. Staff management
12. Services management
13. Dashboard summary and revenue
14. Smart algorithms from the proposal:
    - Dynamic Pricing Algorithm
    - Moving Average Demand Forecast
    - Budget-based Room Recommendation

DEFAULT LOGIN
Username: admin
Password: admin123

Reception login:
Username: reception
Password: reception123

RUN ORDER
1. Start MySQL.
2. Create database using database/smart_stay.sql.
3. Open backend-smartstay-api in IntelliJ and run HotelApiApplication.java.
4. Open frontend-smartstay-javafx in another IntelliJ window and run MainApp.java.

IMPORTANT MYSQL SETTING
Open backend-smartstay-api/src/main/resources/application.properties and set your MySQL password:
spring.datasource.password=YOUR_PASSWORD

API BASE URL
http://localhost:8080/api

SMART API EXAMPLES
GET http://localhost:8080/api/smart/dynamic-pricing
GET http://localhost:8080/api/smart/recommend-room?budget=3500
GET http://localhost:8080/api/smart/forecast-demand
