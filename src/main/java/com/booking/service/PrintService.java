package com.booking.service;

import java.util.List;
import java.util.stream.Collectors;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {
    public static void printMenu(String title, String[] menuArr) {
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);
            num++;
        }
    }

    public String printServices(List<Service> serviceList) {
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public void showRecentReservation(List<Reservation> reservationList) {
        int num = 1;
        List<Reservation> inProcessReservations = reservationList.stream()
                .filter(reservation -> "In Process".equalsIgnoreCase(reservation.getWorkstage()))
                .collect(Collectors.toList());
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println(
                "+================================================================================================+");
        for (Reservation reservation : inProcessReservations) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting")
                    || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                        num, reservation.getReservationId(), reservation.getCustomer().getName(),
                        printServices(reservation.getServices()), reservation.getReservationPrice(),
                        reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
    }

    public void showAllCustomer(List<Person> personList) {
        System.out.println("List of All Customers:");
        System.out.printf("| %-4s | %-11s | %-15s | %-15s | %-15s |\n", "No.", "ID", "Nama Customer", "Member Status",
                "Wallet");
        System.out.println("+==========================================================================+");
        int num = 1;
        for (Person person : personList) {
            if (person instanceof Customer) {
                Customer customer = (Customer) person;
                System.out.printf("| %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                        num, customer.getId(), customer.getName(), customer.getMember().getMembershipName(),
                        customer.getWallet());
                num++;
            }
        }
    }

    public void showAllEmployee(List<Person> personList) {
        System.out.println("List of All Employees:");
        System.out.printf("| %-4s | %-11s | %-15s | %-15s |\n", "No.", "ID", "Nama Employee", "Experience");
        System.out.println("+========================================================+");
        int num = 1;
        for (Person person : personList) {
            if (person instanceof Employee) {
                Employee employee = (Employee) person;
                System.out.printf("| %-4s | %-11s | %-15s | %-15s |\n",
                        num, employee.getId(), employee.getName(), employee.getExperience());
                num++;
            }
        }
    }

    public void showHistoryReservation(List<Reservation> reservationList) {
        System.out.println("List of Reservation History:");
        System.out.printf("| %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Customer Name", "Service", "Service's Price", "Pegawai", "Workstage");
        System.out
                .println("+========================================================================================+");
        int num = 1;
        for (Reservation reservation : reservationList) {
            System.out.printf("| %-4s | %-11s | %-15s | %-15s | %-15s | %-15s | %-10s |\n",
                    num, reservation.getReservationId(), reservation.getCustomer().getName(),
                    printServices(reservation.getServices()), reservation.getReservationPrice(),
                    reservation.getEmployee().getName(), reservation.getWorkstage());
            num++;
        }
    }

    public void showAvailableService(List<Service> serviceList) {
        System.out.println("List of Service:");
        System.out.printf("| %-4s | %-11s | %-20s | %-15s |\n",
                "No.", "ID", "Service Name", "Price");
        System.out
                .println("+=============================================================+");
        int num = 1;
        for (Service service : serviceList) {
            System.out.printf("| %-4s | %-11s | %-20s | %-15s |\n",
                    num, service.getServiceId(), service.getServiceName(), service.getPrice());
            num++;
        }
    }
}
