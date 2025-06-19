# ✈️ Flight Booking Project

This is a distributed microservices project built using **Spring Boot**, **Apache Kafka**, and **PostgreSQL**, consisting of two main services:

- `flight-booking-service`: Handles flight listings and bookings.
- `ticket-generator-service`: Listens to booking events and sends ticket confirmation emails. [Project Link](https://github.com/Pratishthan/ticket-generator-service)



## 🏗️ Architecture Overview

             +----------------------+
             |  Flight Booking API  |
             +----------------------+
                      |
                      v
            [ flight-booking-service ]
                      |
           +----------+----------+
           |                     |
     [ PostgreSQL DB ]     [ Kafka Producer ]
                              |
                              v
                    🔊 Topic: "bookings"
                              |
                              v
                    [ Kafka Consumer ]
                              |
                              v  
                    [ ticket-generator-service ]
                              |
                              v  
                    [ Email Service ]

## 🧰 Tech Stack

| Layer             | Tools Used                         |
|------------------|-------------------------------------|
| Language          | Java 17                             |
| Framework         | Spring Boot                         |
| Messaging         | Apache Kafka                        |
| Persistence       | PostgreSQL + Spring Data JPA        |
| Email             | Spring Boot Mail (Gmail SMTP)       |
| API Protocol      | REST (Spring Web)                   |
| Data Serialization| JSON                                |
| Dev Tools         | Spring DevTools, Lombok             |

---

## 🧩 Microservices Overview

### 📦 1. `flight-booking-service`

Handles:
- Flight creation
- Flight search (by route or number)
- Booking seats on a flight
- Produces Kafka event (`BookingEvent`) after successful booking

#### Endpoints:

| Method | Path                                 | Description                            |
|--------|--------------------------------------|----------------------------------------|
| POST   | `/flight`                            | Add a new flight                       |
| GET    | `/flight/`                           | Fetch all flights                      |
| GET    | `/flight/number/{flightNumber}`      | Get flight by number                   |
| GET    | `/flight/route/{src}/{dest}`         | Get flights by route                   |
| POST   | `/booking/flight/{flightId}`         | Book a flight                          |

#### Kafka Producer:

Sends the following event to topic `bookings`:
```json
{
  "bookingId": 5,
  "passengerName": "example",
  "email":"example@gmail.com",
  "seatsBooked": 2,
  "flightNumber": "AI-101",
  "bookingTime": "2025-06-18T15:00:00",
  "status": "CONFIRMED"
}
```
### 📦 2. `ticket-generator-service`

Listens to the `bookings` Kafka topic and:

* Consumes `BookingEvent` messages and deserializes it
* Sends plain-text confirmation emails to passengers

#### Email Example:

```
Hello Example,

Your booking is confirmed!

✈️ Flight: AI-101
👤 Passenger: example
💺 Seats: 2
📅 Time: 2025-06-18T15:00
📦 Status: CONFIRMED

Thank you for booking with us!
```

---

## 🧪 Sample Booking Flow

1. Add a flight via `POST /flight`
2. Book the flight via `POST /booking/flight/{flightId}?passengerName=example&email=example@gmail.com&seats=2`
3. Booking is stored and event is published to Kafka
4. `ticket-generator-service` consumes it and sends a confirmation email

---

## ⚙️ Kafka Setup (Local)

1. Download and extract Kafka from [Apache Kafka Downloads](https://kafka.apache.org/downloads)
2. Start ZooKeeper:

   ```
   bin/zookeeper-server-start.sh config/zookeeper.properties
   ```
3. Start Kafka broker:

   ```
   bin/kafka-server-start.sh config/server.properties
   ```


##⚙️ Email Setup
In ticket-generator-service/src/main/resources/application.yml:

spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: your_email@gmail.com
    password: your_app_password
    properties:
    mail:
      smtp:
      auth: true
      starttls:
      enable: true


✅ Email is sent to the actual address received in the booking request.

## 🚀 Future Scope (Planned Phases)

### 🟡 **Phase 2: Architecture Improvements**

> Focus: Improve scalability, decoupling, and performance

| Feature                                              | Technology/Strategy                | Description                                                                                                                                                 |
| ---------------------------------------------------- |------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 🔄 Replace `spring-kafka` with `Spring Cloud Stream` | Spring Cloud Stream Binder (Kafka) | Abstract Kafka logic using bindings instead of KafkaTemplate and annotations. This enables cleaner decoupling and easier broker migration (e.g., RabbitMQ). |
| 🚀 Implement Caching for Flight Search               | Spring Cache                       | Reduce DB load for frequently accessed endpoints like `/flight/route/{source}/{destination}` and `/flight/number/{flightNumber}` using spring cache.        |
| 🧪 Add Unit & Integration Tests                      | JUnit, Cucumber                    | Will add test cases.                                                                                                                                        |

---

### 🔐 **Phase 3: Security & User Experience**

> Focus: Secure the platform and enhance accessibility

| Feature                               | Technology/Strategy               | Description                                                                                                 |
| ------------------------------------- | --------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| 🔐 Add Authentication & Authorization | Spring Security + JWT             | Restrict access to flight and booking APIs. Use roles (`ADMIN`, `USER`) and issue tokens for secure access. |
| 👤 Multi-user support                 | User Entity + Login/Register APIs | Store and authenticate real users (email/password), and link bookings to users.                             |
| 🖥️ Optional UI Layer                 | React / Angular / Thymeleaf       | Build a web frontend for customers to search flights, book tickets, and view booking history.               |
| 🧾 Ticket Portal / History Page       | REST APIs + UI                    | Let users view past bookings and resend ticket emails.                                                      |

---


