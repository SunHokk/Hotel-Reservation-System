package com.Hotel.Reservation;

import java.util.Scanner;

public class Transportation extends Service {

    public Transportation() {
        super();
    }

    @Override
    public void setDetails() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter type of transportation(1/2/3)");
        type = in.nextInt();
        System.out.println("Enter number of people(1/2)");
        quantity = in.nextInt();

        cost = costForType(type);
    }
}
