package com.Hotel.Reservation;

import java.util.Scanner;

public class Customer {

    private static final int UNINITIALIZED = -1;

    int no;
    String name;
    String bookingno;
    int nod;
    int type;
    int ld;
    int d;
    boolean status;

    public Customer() {
        no = UNINITIALIZED;
        name = null;
        bookingno = null;
        nod = UNINITIALIZED;
        type = UNINITIALIZED;
        ld = UNINITIALIZED;
        d = UNINITIALIZED;
        status = false;
    }

    public void setInitialDetails(int customerNo) {
        no = customerNo;

        Scanner in = new Scanner(System.in);
        System.out.println("Enter name");
        name = in.nextLine();
        System.out.println("Enter room type?1 for Luxury,2 for Deluxe,3 for superdeluxe");
        type = in.nextInt();
        System.out.println("Enter occupancy? 1/2");
        ld = in.nextInt();
        System.out.println("Enter number of days?");
        d = in.nextInt();
    }

    public void setBookingNo(String bookingNo) {
        this.bookingno = bookingNo;
    }
}
