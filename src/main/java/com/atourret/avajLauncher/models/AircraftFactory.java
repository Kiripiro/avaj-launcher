package com.atourret.avajLauncher.models;

import com.atourret.avajLauncher.interfaces.Flyable;

public class AircraftFactory {
    private static AircraftFactory instance = null;
    private static long id = 0;

    private AircraftFactory() {
    }

    public static AircraftFactory getInstance() {
        if (instance == null)
            instance = new AircraftFactory();
        return instance;
    }

    public static Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        switch (p_type) {
            case "Helicopter":
                return new Helicopter(id++, p_name, p_coordinates);
            case "Baloon":
                return new Baloon(id++, p_name, p_coordinates);
            case "JetPlane":
                return new JetPlane(id++, p_name, p_coordinates);
            default:
                return null;
        }
    }
}
