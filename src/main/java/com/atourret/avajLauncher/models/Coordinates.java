package com.atourret.avajLauncher.models;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;
    private static final int MAX_HEIGHT = 100;

    public Coordinates(int p_longitude, int p_latitude, int p_height) {
        this.longitude = p_longitude;
        this.latitude = p_latitude;
        this.height = p_height;
        height = Math.min(height, MAX_HEIGHT);
    }

    public int getLongitude() {
        return this.longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Coordinates: [height=" + height
                + ", latitude=" + latitude
                + ", longitude=" + longitude
                + "]";
    }
}
