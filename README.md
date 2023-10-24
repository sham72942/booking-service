# Mobile Device Booking System

## Overview

This project implements a Mobile Device Booking System for managing the availability and booking of mobile devices. It allows employees to book or return mobile devices for testing purposes. This README provides an overview of the project's database design, API endpoints, and JSON models.

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

## JSON Models

### Device
```json
{
    "id": 1,
    "imei": "1234554321",
    "name": "Samsung S23",
    "description": "Some Samsung mobile",
    "available": true,
    "booked_at": "",
    "booked_by": 1234
}
```

### Employee
```json
{
    "id": 200,
    "userName": "usr",
    "name": "Shamoel",
    "age": 30,
    "address": "Ranchi"
}
```

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
  "action": "book/return"
}
```