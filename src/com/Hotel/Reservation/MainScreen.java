package com.Hotel.Reservation;

import java.io.Serializable;
import java.util.Scanner;

public class MainScreen implements Serializable {

    // Replace Magic Number with Symbolic Constant (refactoring.guru)
    private static final int NUM_LUXURY_ROOMS = 3;
    private static final int NUM_DELUXE_ROOMS = 8;
    private static final int NUM_SUPER_DELUXE_ROOMS = 10;
    private static final int MAX_CUSTOMERS = 20;
    private static final int SERVICE_DIMENSION_2 = 5;
    private static final int SERVICE_DIMENSION_3 = 5;

    private static final int RATE_LUXURY = 500;
    private static final int RATE_DELUXE = 1500;
    private static final int RATE_SUPER_DELUXE = 2500;

    private static final boolean WIFI_NOT_AVAILABLE = false;
    private static final boolean WIFI_AVAILABLE = true;
    private static final boolean ROOM_VACANT = false;

    private static final int ROOM_TYPE_LUXURY = 1;
    private static final int ROOM_TYPE_DELUXE = 2;
    private static final int ROOM_TYPE_SUPER_DELUXE = 3;

    private static final int FLAG_NO_ROOM = 0;
    private static final int FLAG_HAS_ROOM = 1;

    private static final char ACTION_BOOK = 'b';
    private static final char ACTION_CANCEL = 'c';
    private static final char ACTION_SERVICE = 's';
    private static final char ACTION_EXIT = 'e';

    private static final int SERVICE_TRANSPORT = 1;
    private static final int SERVICE_LAUNDRY = 2;

    private static final int CHAR_TO_INT_OFFSET = 48;

    public static void main(String args[]) {
        Luxury[] luxuryRooms = new Luxury[NUM_LUXURY_ROOMS];
        Deluxe[] deluxeRooms = new Deluxe[NUM_DELUXE_ROOMS];
        SuperDeluxe[] superDeluxeRooms = new SuperDeluxe[NUM_SUPER_DELUXE_ROOMS];
        Customer[] customers = new Customer[MAX_CUSTOMERS];
        Laundry[][][] laundryServices = new Laundry[MAX_CUSTOMERS][SERVICE_DIMENSION_2][SERVICE_DIMENSION_3];
        Transportation[][][] transportServices = new Transportation[MAX_CUSTOMERS][SERVICE_DIMENSION_2][SERVICE_DIMENSION_3];
        Book[] bookings = new Book[MAX_CUSTOMERS];

        initLuxuryRooms(luxuryRooms);
        initDeluxeRooms(deluxeRooms);
        initSuperDeluxeRooms(superDeluxeRooms);
        initCustomers(customers);
        initBookings(bookings);
        initServices(transportServices, laundryServices);

        runMenuLoop(luxuryRooms, deluxeRooms, superDeluxeRooms,
                customers, bookings, transportServices, laundryServices);
    }

    // Extract Method — masing-masing init terpisah, tidak lagi numpang di main()
    private static void initLuxuryRooms(Luxury[] rooms) {
        for (int i = 0; i < NUM_LUXURY_ROOMS; i++) {
            rooms[i] = new Luxury();
            rooms[i].set(RATE_LUXURY, WIFI_NOT_AVAILABLE, ROOM_VACANT);
        }
    }

    private static void initDeluxeRooms(Deluxe[] rooms) {
        for (int i = 0; i < NUM_DELUXE_ROOMS; i++) {
            rooms[i] = new Deluxe();
            rooms[i].set(RATE_DELUXE, WIFI_AVAILABLE, ROOM_VACANT);
        }
    }

    private static void initSuperDeluxeRooms(SuperDeluxe[] rooms) {
        for (int i = 0; i < NUM_SUPER_DELUXE_ROOMS; i++) {
            rooms[i] = new SuperDeluxe();
            rooms[i].set(RATE_SUPER_DELUXE, WIFI_AVAILABLE, ROOM_VACANT);
        }
    }

    private static void initCustomers(Customer[] customers) {
        for (int i = 0; i < MAX_CUSTOMERS; i++) {
            customers[i] = new Customer();
        }
    }

    private static void initBookings(Book[] bookings) {
        for (int i = 0; i < MAX_CUSTOMERS; i++) {
            bookings[i] = new Book();
        }
    }

    private static void initServices(Transportation[][][] transport, Laundry[][][] laundry) {
        for (int i = 0; i < MAX_CUSTOMERS; i++) {
            for (int j = 0; j < SERVICE_DIMENSION_2; j++) {
                for (int k = 0; k < SERVICE_DIMENSION_3; k++) {
                    transport[i][j][k] = new Transportation();
                    laundry[i][j][k] = new Laundry();
                }
            }
        }
    }

    private static void displayMenu() {
        System.out.println("What do you want to do?");
        System.out.println("Book a room(b)");
        System.out.println("Avail a service(s)");
        System.out.println("Cancel a booked room(c)");
        System.out.println("Exit Menu(e)");
    }

    private static void runMenuLoop(Luxury[] luxuryRooms, Deluxe[] deluxeRooms,
                                    SuperDeluxe[] superDeluxeRooms, Customer[] customers,
                                    Book[] bookings, Transportation[][][] transport,
                                    Laundry[][][] laundry) {
        int customerIndex = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            displayMenu();
            char action = in.next(".").charAt(0);

            if (action == ACTION_BOOK) {
                int luxuryIdx = findFirstAvailable(luxuryRooms);
                int deluxeIdx = findFirstAvailable(deluxeRooms);
                int superDeluxeIdx = findFirstAvailable(superDeluxeRooms);

                int flagLuxury = (luxuryIdx >= 0) ? FLAG_HAS_ROOM : FLAG_NO_ROOM;
                int flagDeluxe = (deluxeIdx >= 0) ? FLAG_HAS_ROOM : FLAG_NO_ROOM;
                int flagSuperDeluxe = (superDeluxeIdx >= 0) ? FLAG_HAS_ROOM : FLAG_NO_ROOM;

                if (luxuryIdx < 0) luxuryIdx = 0;
                if (deluxeIdx < 0) deluxeIdx = 0;
                if (superDeluxeIdx < 0) superDeluxeIdx = 0;

                customers[customerIndex].setInitialDetails(customerIndex);
                bookings[customerIndex].BookNew(customers[customerIndex],
                        luxuryRooms[luxuryIdx], deluxeRooms[deluxeIdx], superDeluxeRooms[superDeluxeIdx],
                        transport, laundry,
                        luxuryIdx, flagLuxury, deluxeIdx, flagDeluxe, superDeluxeIdx, flagSuperDeluxe);
                customers[customerIndex].setBookingNo(bookings[customerIndex].getBookingNumber());
                customerIndex++;
            }

            if (action == ACTION_CANCEL) {
                customerIndex--;
                handleCancel(luxuryRooms, deluxeRooms, superDeluxeRooms);
            }

            if (action == ACTION_SERVICE) {
                handleService(customers, bookings, transport, laundry);
            }

            if (action == ACTION_EXIT) {
                break;
            }
        }
    }

    // Extract Method — pencarian first available, gantikan 3 loop duplikat di main()
    private static int findFirstAvailable(Room[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            if (!rooms[i].getStatus()) {
                return i;
            }
        }
        return -1;
    }

    private static void handleCancel(Luxury[] luxuryRooms, Deluxe[] deluxeRooms,
                                     SuperDeluxe[] superDeluxeRooms) {
        Scanner in2 = new Scanner(System.in);
        System.out.println("Enter your booking no");
        String bookingNo = in2.nextLine();

        int roomIdx = (int) bookingNo.charAt(0) - CHAR_TO_INT_OFFSET;

        System.out.println("Enter type(1-lux,2-delux,3-super)");
        int roomType = in2.nextInt();

        if (roomType == ROOM_TYPE_LUXURY) {
            luxuryRooms[roomIdx].statuschange();
        }
        if (roomType == ROOM_TYPE_DELUXE) {
            deluxeRooms[roomIdx].statuschange();
        }
        if (roomType == ROOM_TYPE_SUPER_DELUXE) {
            superDeluxeRooms[roomIdx].statuschange();
        }

        System.out.println("Cancelled");
    }

    private static void handleService(Customer[] customers, Book[] bookings,
                                      Transportation[][][] transport, Laundry[][][] laundry) {
        Scanner in2 = new Scanner(System.in);

        System.out.println("Enter your booking no");
        String bookingNo = in2.nextLine();

        System.out.println("Enter your customer no");
        int customerNo = in2.nextInt();

        System.out.println("Enter the service required(Transport(1)/Laundry(2))");
        int serviceType = in2.nextInt();

        int roomIdx = (int) (bookingNo.charAt(0)) - CHAR_TO_INT_OFFSET;
        int occupancyDigit;
        if (bookingNo.charAt(1) == 's') {
            occupancyDigit = (int) (bookingNo.charAt(4)) - CHAR_TO_INT_OFFSET;
        } else {
            occupancyDigit = (int) (bookingNo.charAt(3)) - CHAR_TO_INT_OFFSET;
        }

        if (serviceType == SERVICE_TRANSPORT) {
            transport[roomIdx][occupancyDigit][0].setDetails();
            bookings[customerNo].s1 = bookings[customerNo].s1
                    + transport[roomIdx][occupancyDigit][0].getTotalCost();
            bookings[customerNo].BookDisplay(bookings[customerNo].ff, customers[customerNo].name,
                    bookings[customerNo].s1, bookings[customerNo].s2, bookingNo);
        }
        if (serviceType == SERVICE_LAUNDRY) {
            laundry[roomIdx][occupancyDigit][1].setDetails();
            bookings[customerNo].s2 = bookings[customerNo].s2
                    + laundry[roomIdx][occupancyDigit][1].getTotalCost();
            bookings[customerNo].BookDisplay(bookings[customerNo].ff, customers[customerNo].name,
                    bookings[customerNo].s1, bookings[customerNo].s2, bookingNo);
        }
    }
}
