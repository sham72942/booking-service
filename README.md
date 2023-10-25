# Mobile Device Booking System

## Overview

This project implements a Mobile Device Booking System for managing the availability and booking of mobile devices. It allows employees to book or return mobile devices for testing purposes. This README provides an overview of the project's database design, API endpoints, and JSON API models.

## Running the Project
```
docker-compose up --build
```
docker-compose up is setup using docker-compose.yml file, it has 2 services, the first is a postgres being run at 5433 port and the second is the application being run at 8080 port.

The app docker container is created using Dockerfile, it has 2 stages, the first stage is to build the application using maven and the second stage is to run the application using java.

## Testing the Project
### Testing is done using postman & Jmeter
```agsl
Postman Collection: https://api.postman.com/collections/13375656-57883421-9d2c-44ce-a484-9654a3c51f5b?access_key=PMAT-01HDK3FDXMSJKQCKV1RJ5FEQNZ
```
```agsl
jmx file is attached in the project root directory
```

## Database Schema Design

### *Employee*
- `id`: Integer (Primary Key, Auto-increment)
- `key`: String (Unique)
- `name`: String (Not Null)
- `age`: Integer (Not Null)
- `address`: String (Nullable)

### *Device*
- `id`: Integer (Primary Ky, Auto-increment)
- `IMEI`: String(Unique)
- `model`: String (Not Null)
- `available`: Boolean (Not Null)
- `description`: String (Nullable)

### *Booking*
#### Acts as audit trail for booking, store all booking information.
- `id`: Integer (Primary Key, Auto-increment)
- `device_id`: Integer (Foreign Key to Device, Not Null)
- `booked_at`: Timestamp with Time Zone (Not Null)
- `returned_at`: Timestamp with Time Zone (Nullable)
- `booked_by`: Integer (Foreign Key to Employee, Not Null)

## API Design

### Create Booking
- **Endpoint**: POST /booking
- **Request**: Booking object
- **Description**: Allows employees to book a device. The action is specified in the `Booking` object.

### Return Device
- **Endpoint**: POST /booking
- **Request**: Booking object
- **Description**: Allows employees to return a device. The action is specified in the `Booking` object.

### Get Device Details
- **Endpoint**: GET /devices/{id}
- **Response**: Device object
- **Description**: Retrieves details of a specific device by its ID.

### List All Devices
- **Endpoint**: GET /devices
- **Response**: List of device objects
- **Description**: Retrieves a list of all available devices.

[//]: # (### Get Device Status)

[//]: # (- **Endpoint**: GET /device-status/{device id})

[//]: # (- **Response**: Device status object)

[//]: # (- **Description**: Retrieves the status of a specific device by its ID.)

[//]: # ()
[//]: # (### List All Device Status)

[//]: # (- **Endpoint**: GET /devices-status)

[//]: # (- **Response**: List of device status objects)

[//]: # (- **Description**: Retrieves the status of all devices.)

## JSON API Models

### Device
```json
{
    "id": 1,
    "imei": "1234554321",
    "model": "Samsung S23",
    "available": true,
    "description": "Some Samsung mobile",
    "bookedAt": "",
    "bookedBy": 1234
}
```

[//]: # (### Employee)

[//]: # (```json)

[//]: # ({)

[//]: # (    "id": 200,)

[//]: # (    "userName": "usr",)

[//]: # (    "name": "Shamoel",)

[//]: # (    "age": 30,)

[//]: # (    "address": "Ranchi")

[//]: # (})

[//]: # (```)

[//]: # (### Device Status)

[//]: # (```json)

[//]: # ({)

[//]: # (  "deviceId": 1,)

[//]: # (  "available": true,)

[//]: # (  "lastBooked": "23-10-2023 12:00 GMT",)

[//]: # (  "bookedBy": {)

[//]: # (    "id": 200,)

[//]: # (    "userName": "usr",)

[//]: # (    "name": "Shamoel",)

[//]: # (    "age": 30,)

[//]: # (    "address": "Ranchi")

[//]: # (  })

[//]: # (})

[//]: # (```)

### Booking
```json
{
  "deviceId": 1,
  "employeeId": 2,
  "action": "BOOK/RETURN"
}
```