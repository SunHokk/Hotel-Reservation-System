package com.Hotel.Reservation;

public abstract class Service {

    protected static final int COST_TYPE_1 = 100;
    protected static final int COST_TYPE_2 = 200;
    protected static final int COST_TYPE_3 = 300;

    protected int type;
    protected int cost;
    protected int quantity;
    protected String bno;
    protected boolean status;

    protected Service() {
        type = 0;
        cost = 0;
        quantity = 0;
        bno = null;
        status = false;
    }

    public abstract void setDetails();

    public int getTotalCost() {
        return quantity * cost;
    }

    public boolean getStatus() {
        return status;
    }

    protected int costForType(int type) {
        switch (type) {
            case 1: return COST_TYPE_1;
            case 2: return COST_TYPE_2;
            case 3: return COST_TYPE_3;
            default: return 0;
        }
    }
}
