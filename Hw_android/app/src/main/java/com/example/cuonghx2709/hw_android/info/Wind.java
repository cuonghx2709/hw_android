package com.example.cuonghx2709.hw_android.info;

/**
 * Created by cuonghx2709 on 10/13/2017.
 */

public class Wind {
    private float speed;
    private double deg;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    public Wind(float speed, double deg) {

        this.speed = speed;
        this.deg = deg;
    }
}
