package com.Hotel.Reservation;

import java.io.Serializable;
import java.util.Scanner;

public class Book implements Serializable {

    private static final int ROOM_TYPE_LUXURY = 1;
    private static final int ROOM_TYPE_DELUXE = 2;
    private static final int ROOM_TYPE_SUPER_DELUXE = 3;

    private static final int OCCUPANCY_SINGLE = 1;
    private static final int OCCUPANCY_DOUBLE = 2;

    private static final int FLAG_ROOM_AVAILABLE = 1;

    private static final int LAUNDRY_LANE = 1;
    private static final int TRANSPORT_LANE = 0;

    String bookno;
    Transportation t[][][];
    Laundry l[][][];
    int ff;
    Customer cust;
    int s1 = 0;
    int s2 = 0;

    public String getBookingNumber() {
        return bookno;
    }

    public void BookNew(Customer c, Luxury ly, Deluxe dx, SuperDeluxe sdx,
                        Transportation tr[][][], Laundry lr[][][],
                        int ily, int flag1, int idx, int flag2, int isdx, int flag3) {
        cust = c;
        t = tr;
        l = lr;

        if (c.type == ROOM_TYPE_LUXURY) {
            handleLuxuryRequest(c, ly, dx, sdx, ily, flag1, idx, isdx);
        }
        if (c.type == ROOM_TYPE_DELUXE) {
            handleDeluxeRequest(c, ly, dx, sdx, ily, idx, flag2, isdx);
        }
        if (c.type == ROOM_TYPE_SUPER_DELUXE) {
            handleSuperDeluxeRequest(c, ly, dx, sdx, ily, idx, isdx, flag3);
        }
    }

    private void handleLuxuryRequest(Customer c, Luxury ly, Deluxe dx, SuperDeluxe sdx,
                                     int ily, int flag1, int idx, int isdx) {
        if (flag1 != FLAG_ROOM_AVAILABLE) {
            System.out.println("No Luxury Rooms Available");
            Scanner in5 = new Scanner(System.in);

            System.out.println("Do you want any other room? Deluxe(2) or SuperDeluxe(3) ");
            int g = in5.nextInt();

            if (g == ROOM_TYPE_DELUXE) {
                BookDeluxe(c, dx, g, idx, c.ld, c.d);
            }
            if (g == ROOM_TYPE_SUPER_DELUXE) {
                BookSuperDeluxe(c, sdx, g, isdx, c.ld, c.d);
            }
        } else {
            BookLuxury(c, ly, c.type, ily, c.ld, c.d);
        }
    }

    private void handleDeluxeRequest(Customer c, Luxury ly, Deluxe dx, SuperDeluxe sdx,
                                     int ily, int idx, int flag2, int isdx) {
        if (flag2 != FLAG_ROOM_AVAILABLE) {
            System.out.println("No Deluxe Room Available");
            Scanner in6 = new Scanner(System.in);

            System.out.println("Do you want any other room? Luxury(1) or SuperDeluxe(3) ");
            int g = in6.nextInt();

            if (g == ROOM_TYPE_DELUXE) {
                BookLuxury(c, ly, g, ily, c.ld, c.d);
            }
            if (g == ROOM_TYPE_SUPER_DELUXE) {
                BookSuperDeluxe(c, sdx, g, isdx, c.ld, c.d);
            }
        } else {
            BookDeluxe(c, dx, c.type, idx, c.ld, c.d);
        }
    }

    private void handleSuperDeluxeRequest(Customer c, Luxury ly, Deluxe dx, SuperDeluxe sdx,
                                          int ily, int idx, int isdx, int flag3) {
        if (flag3 != FLAG_ROOM_AVAILABLE) {
            System.out.println("No SuperDeluxe Room Available");
            Scanner in5 = new Scanner(System.in);

            System.out.println("Do you want any other room? Deluxe(2) or Luxury(3) ");
            int g = in5.nextInt();

            if (g == ROOM_TYPE_DELUXE) {
                BookDeluxe(c, dx, g, idx, c.ld, c.d);
            }
            if (g == ROOM_TYPE_LUXURY) {
                BookLuxury(c, ly, g, ily, c.ld, c.d);
            }
        } else {
            BookSuperDeluxe(c, sdx, c.type, isdx, c.ld, c.d);
        }
    }

    public void BookLuxury(Customer c, Luxury ly, int type, int ily, int ld, int d) {
        ly.statuschange();
        Fare f = new Fare();

        if (ld == OCCUPANCY_SINGLE) {
            System.out.println("Single Luxury Room is booked");
            ff = f.farecalculator(d, ly.rate, ld);
            bookno = ily + "lx1";
            BookDisplay(ff, c.name, t[ily][0][0].getTotalCost(),
                    l[ily][0][LAUNDRY_LANE].getTotalCost(), bookno);
        }
        if (ld == OCCUPANCY_DOUBLE) {
            System.out.println("Double Luxury Room is booked");
            ff = f.farecalculator(d, ly.rate, ld);
            bookno = ily + "lx2";
            BookDisplay(ff, c.name, t[ily][1][TRANSPORT_LANE].getTotalCost(),
                    l[ily][1][LAUNDRY_LANE].getTotalCost(), bookno);
        }
    }

    public void BookDeluxe(Customer c, Deluxe dx, int type, int idx, int ld, int d) {
        dx.statuschange();
        Fare f = new Fare();

        if (ld == OCCUPANCY_SINGLE) {
            System.out.println("Single Deluxe Room is booked");
            ff = f.farecalculator(d, dx.rate, ld);
            bookno = idx + "dx1";
            BookDisplay(ff, c.name, t[idx][0][TRANSPORT_LANE].getTotalCost(),
                    l[idx][0][LAUNDRY_LANE].getTotalCost(), bookno);
        }
        if (ld == OCCUPANCY_DOUBLE) {
            System.out.println("Double Deluxe Room is booked");
            ff = f.farecalculator(d, dx.rate, ld);
            bookno = idx + "dx2";
            BookDisplay(ff, c.name, t[idx][1][TRANSPORT_LANE].getTotalCost(),
                    l[idx][1][LAUNDRY_LANE].getTotalCost(), bookno);
        }
    }

    public void BookSuperDeluxe(Customer c, SuperDeluxe sdx, int type, int isdx, int ld, int d) {
        sdx.statuschange();
        Fare f = new Fare();

        if (ld == OCCUPANCY_SINGLE) {
            System.out.println("Single Super Deluxe Room is booked");
            ff = f.farecalculator(d, sdx.rate, ld);
            bookno = isdx + "sdx1";
            BookDisplay(ff, c.name, t[isdx][0][TRANSPORT_LANE].getTotalCost(),
                    l[isdx][0][LAUNDRY_LANE].getTotalCost(), bookno);
        }
        if (ld == OCCUPANCY_DOUBLE) {
            System.out.println("Double Super Deluxe Room is booked");
            ff = f.farecalculator(d, sdx.rate, ld);
            bookno = isdx + "sdx2";
            BookDisplay(ff, c.name, t[isdx][1][TRANSPORT_LANE].getTotalCost(),
                    l[isdx][1][LAUNDRY_LANE].getTotalCost(), bookno);
        }
    }

    public void BookDisplay(int ff, String name, int tr, int lr, String b) {
        System.out.println("Booking number " + bookno);
        System.out.println("Customer number " + cust.no);
        System.out.println("Booking Name " + name);
        System.out.println("Fare is " + ff);
        System.out.println("Total cost of transportation is" + tr);
        System.out.println("Total cost of laundry is" + lr);
    }

    public int getFare() {
        return ff;
    }

    public String getName() {
        return cust.name;
    }
}
