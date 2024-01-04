package com.booking.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

public class ReservationService {

    private static List<Person> personList = PersonRepository.getAllPerson();
    private static List<Service> serviceList = ServiceRepository.getAllService();
    private static Scanner input = new Scanner(System.in);

    private static PrintService printService = new PrintService();

    public static void createReservation(List<Reservation> reservationList) {
        printService.showAllCustomer(personList);
        System.out.println("Enter customer ID for reservation: ");
        String customerId = input.nextLine();

        Customer customer = findCustomerById(customerId);

        if (customer != null) {
            printService.showAllEmployee(personList);
            System.out.println("Enter employee ID for reservation: ");
            String employeeId = input.nextLine();

            Employee employee = findEmployeeById(employeeId);

            if (employee != null) {
                List<Service> selectedServices = selectServices();

                String workstage = selectedWorkStage();

                String reservationId = "Res-" + UUID.randomUUID().toString().substring(0, 6);
                double reservationPrice = calculateReservationPrice(selectedServices);

                Reservation reservation = new Reservation(reservationId, customer, employee, selectedServices,
                        workstage);
                reservation.setReservationPrice(reservationPrice);

                reservationList.add(reservation);
                System.out.println("Reservation created successfully!");
            } else {
                System.out.println("Employee not found!");
            }
        } else {
            System.out.println("Customer not found!");
        }
    }

    public static void editReservationWorkstage(List<Reservation> reservationList) {
        printService.showRecentReservation(reservationList);
        System.out.println("Enter reservation ID to edit workstage: ");
        String reservationId = input.nextLine();

        Reservation reservation = findReservationById(reservationId, reservationList);

        if (reservation != null) {
            String newWorkstage = selectedWorkStage();

            reservation.setWorkstage(newWorkstage);
            System.out.println("Workstage updated successfully!");
        } else {
            System.out.println("Reservation not found!");
        }
    }

    private static Customer findCustomerById(String customerId) {
        return personList.stream()
                .filter(person -> person instanceof Customer && person.getId().equals(customerId))
                .map(person -> (Customer) person)
                .findFirst()
                .orElse(null);
    }

    private static Employee findEmployeeById(String employeeId) {
        return personList.stream()
                .filter(person -> person instanceof Employee && person.getId().equals(employeeId))
                .map(person -> (Employee) person)
                .findFirst()
                .orElse(null);
    }

    private static List<Service> selectServices() {
        printService.showAvailableService(serviceList);
        System.out.println("Enter service IDs (comma-separated) for reservation: ");
        String serviceIdsInput = input.nextLine();
        List<String> selectedIds = Arrays.asList(serviceIdsInput.split(","));

        return serviceList.stream()
                .filter(service -> selectedIds.contains(service.getServiceId()))
                .collect(Collectors.toList());
    }

    private static double calculateReservationPrice(List<Service> selectedServices) {
        return selectedServices.stream()
                .mapToDouble(Service::getPrice)
                .sum();
    }

    private static Reservation findReservationById(String reservationId, List<Reservation> reservationList) {
        return reservationList.stream()
                .filter(reservation -> reservation.getReservationId().equals(reservationId))
                .findFirst()
                .orElse(null);
    }

    public static String selectedWorkStage() {
        int choice = 0;
        String workstage = "";
        System.out.printf("Enter new workstage:%n1. %s%n2. %s%n3. %s%nYour choice: ", "In Process", "Finish",
                "Canceled");
        choice = Integer.valueOf(input.nextLine());
        while (choice > 3 || choice < 1) {
            System.out.println("WorkStage is not valid! Please Try Again:");
            choice = Integer.valueOf(input.nextLine());
        }
        switch (choice) {
            case 1:
                workstage = "In Process";
                break;
            case 2:
                workstage = "Finish";
                break;
            case 3:
                workstage = "Cancel";
                break;
        }
        return workstage;
    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
