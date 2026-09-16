# 🏨 Smart Stay — Hotel Management System

**Smart Stay** is a desktop-based Hotel Management System designed to simplify and automate common hotel operations such as room management, guest management, reservations, check-in/check-out, billing, staff management, and hotel services.

The system uses a **JavaFX desktop frontend**, **Spring Boot REST API backend**, and **MySQL database**.

## 🚀 Technologies Used

* **Java**
* **JavaFX** — Desktop Frontend
* **Spring Boot** — Backend REST API
* **Spring Data JPA / Hibernate** — Database & ORM
* **MySQL** — Database
* **BCrypt** — Password Encryption
* **Maven** — Dependency Management
* **REST API** — Frontend ↔ Backend communication
* **IntelliJ IDEA** — Development Environment

---

## ✨ Features

### 🔐 Authentication

* Login system for different user roles
* BCrypt-encrypted password storage
* Admin and Reception user access

### 🛏️ Room Management

* Add, update, and manage rooms
* Room type and pricing management
* Room availability tracking
* Room status management

### 👤 Customer / Guest Management

* Add and manage guest information
* View guest records
* Manage guest details associated with bookings

### 📅 Reservation & Booking Management

* Create and manage reservations
* Booking status management
* Real-time availability checking
* Prevents conflicting reservations and double booking

### 🚪 Check-in & Check-out

* Guest check-in management
* Guest check-out management
* Updates room availability automatically

### 💰 Billing & Payments

* Automated billing records
* Payment record management
* Booking-related billing information
* Revenue tracking

### 👨‍💼 Staff Management

* Add and manage hotel staff
* Maintain staff information
* Staff role management

### 🛎️ Services Management

* Manage additional hotel services
* Add services to guest bookings
* Track service-related charges

### 📊 Dashboard

* Total rooms
* Available rooms
* Booked/occupied rooms
* Revenue summary
* Hotel operational overview

---

# 🧠 Smart Features & Algorithms

Smart Stay also implements algorithm-based features proposed for the project.

### 💵 1. Dynamic Pricing Algorithm

The system calculates room pricing based on factors such as:

* Room availability
* Occupancy
* Demand

This allows room prices to be adjusted dynamically according to hotel demand.

### 📈 2. Moving Average Demand Forecast

A **Moving Average** approach is used to analyze historical booking data and estimate future demand.

This can help provide an indication of expected booking demand.

### 🏨 3. Budget-Based Room Recommendation

The system recommends suitable rooms based on the customer's specified budget.

Example:

```text
GET /api/smart/recommend-room?budget=3500
```

The system returns rooms that match the specified budget.

---

# 🏗️ System Architecture

Smart Stay follows a layered architecture:

```text
                 ┌──────────────────────┐
                 │   JavaFX Frontend    │
                 │    Desktop Client    │
                 └──────────┬───────────┘
                            │
                         REST API
                            │
                 ┌──────────▼───────────┐
                 │   Spring Boot API    │
                 │      Backend         │
                 └──────────┬───────────┘
                            │
                 ┌──────────▼───────────┐
                 │ Spring Data JPA /    │
                 │     Hibernate        │
                 └──────────┬───────────┘
                            │
                 ┌──────────▼───────────┐
                 │       MySQL          │
                 │      Database        │
                 └──────────────────────┘
```

The project is separated into:

```text
Smart Stay
├── backend-smartstay-api
├── frontend-smartstay-javafx
└── database
    └── smart_stay.sql
```

---

# ⚙️ Installation & Setup

## 1. Start MySQL

Make sure your MySQL server is running.

## 2. Create the Database

Use the SQL script included in the project:

```text
database/smart_stay.sql
```

Import this script into MySQL to create the required database and tables.

## 3. Configure Database Password

Open:

```text
backend-smartstay-api/src/main/resources/application.properties
```

Update your MySQL password:

```properties
spring.datasource.password=YOUR_PASSWORD
```

Do not commit your actual database password to a public GitHub repository.

## 4. Start the Backend

Open the following project in IntelliJ IDEA:

```text
backend-smartstay-api
```

Run:

```text
HotelApiApplication.java
```

The Spring Boot backend should start on:

```text
http://localhost:8080
```

## 5. Start the JavaFX Frontend

Open:

```text
frontend-smartstay-javafx
```

in another IntelliJ IDEA window.

Run:

```text
MainApp.java
```

The JavaFX application will communicate with the Spring Boot backend through the REST API.

---



# 🔑 Demo Login

The project includes demo accounts for testing.

### Admin

```text
Username: admin
Password: admin123
```

### Reception

```text
Username: reception
Password: reception123
```

> **Security note:** These are demo credentials for the development project. Change or remove them before deploying the application in a real production environment.

---

# 📂 Project Modules

```text
Smart Stay
│
├── Authentication
├── Dashboard
├── Room Management
├── Customer Management
├── Booking Management
├── Check-in / Check-out
├── Billing & Payments
├── Staff Management
├── Services Management
└── Smart Features
    ├── Dynamic Pricing
    ├── Demand Forecasting
    └── Room Recommendation
```

---

# 🎯 Project Objectives

* Develop a user-friendly hotel management system.
* Automate common hotel management operations.
* Reduce room booking conflicts and prevent double booking.
* Maintain centralized guest, room, booking, staff, and payment records.
* Provide useful dashboard summaries and revenue information.
* Apply algorithmic approaches to improve hotel management decisions.
* Demonstrate practical implementation of Java, Spring Boot, JavaFX, and MySQL.

---

# 🔮 Future Improvements

Possible future improvements include:

* Online hotel booking
* Email/SMS booking notifications
* Advanced revenue analytics
* Machine-learning-based demand prediction
* Online payment gateway integration
* Cloud deployment
* Mobile application
* More advanced role-based access control

---

# 👨‍💻 Author

GitHub: [Shreejesh Panta](https://github.com/shreejeshpanta)

Bachelor of Information Technology (BIT)
GitHub: shreejeshpanta

---

## 📚 Project Purpose

Smart Stay was developed as an academic/final-year project to gain practical experience in **Java application development, Spring Boot backend development, REST APIs, JavaFX desktop application development, database management, authentication, and algorithm implementation**.
