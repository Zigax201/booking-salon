# Booking System Project

This is a simple booking system project written in Java. It allows users to create reservations, manage customers and employees, and perform various other functionalities.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Project Structure](#project-structure)
- [How to Run](#how-to-run)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Booking System Project is designed to manage reservations, customers, and employees in a salon or service-based business. It uses Java programming language and follows a simple console-based user interface.

## Features

- **Create Reservation**: Users can create reservations by selecting a customer, an available employee, and services.
- **Edit Workstage**: Users can edit the workstage of a reservation (e.g., In Process, Finish, Canceled).
- **Show Data**: Various functionalities to display recent reservations, all customers, all employees, and reservation history.
- **Interactive Menu System**: The project features an interactive console-based menu system.

## Project Structure

The project is structured into several packages:

- `com.booking.models`: Contains the model classes for Customer, Employee, Membership, Person, Reservation, and Service.
- `com.booking.repositories`: Provides mock repositories for persons and services.
- `com.booking.service`: Includes service classes for managing reservations, printing data, and validating input.

## How to Run

To run the project, follow these steps:

1. Clone the repository:
   ```bash
   git clone <repository_url>

   cd booking-system-project
2. Compile the Java files:
    ```bash
    javac com/booking/Main.java
3. Run the main class:
    ```bash
    java com.booking.Main

## Usage
Follow the on-screen instructions to navigate through the menu and perform various actions. Use the menu options to create reservations, display data, and manage the system.

## Contributing
If you would like to contribute to the project, feel free to fork the repository and submit pull requests with your changes. Issues and feature requests are also welcome.