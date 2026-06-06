package com.Hotel.Reservation;

import java.util.Scanner;

public class Laundry extends Service {

    public Laundry() {
        super();
    }

    @Override
    public void setDetails() {
        status = true;
        Scanner in = new Scanner(System.in);

        System.out.println("Enter type of wash(1/2/3)");
        type = in.nextInt();
        System.out.println("Enter quantity of clothes");
        quantity = in.nextInt();

        cost = costForType(type);
    }
}
