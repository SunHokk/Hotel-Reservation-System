package com.Hotel.Reservation;

public abstract class Room {

    protected int rate;
    protected boolean wifi;
    protected boolean status;

    public void set(int rate, boolean wifi, boolean status) {
        this.rate = rate;
        this.wifi = wifi;
        this.status = status;
    }

    public int getRate() {
        return rate;
    }

    public boolean getStatus() {
        return status;
    }

    public boolean getWifi() {
        return wifi;
    }

    public void statuschange() {
        status = !status;
    }
}
