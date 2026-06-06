package com.Hotel.Reservation;

public class Fare {

    private static final int OCCUPANCY_SINGLE = 1;
    private static final int OCCUPANCY_DOUBLE = 2;

    public int farecalculator(int days, int rate, int occupancy) {
        if (occupancy == OCCUPANCY_SINGLE) {
            return days * rate;
        }
        if (occupancy == OCCUPANCY_DOUBLE) {
            return days * rate * OCCUPANCY_DOUBLE;
        }
        return 0;
    }
}
