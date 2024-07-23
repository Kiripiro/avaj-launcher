package com.atourret.avajLauncher.services;

import java.util.Random;

import com.atourret.avajLauncher.models.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {

        int seed = p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight();
        seed += new Random().nextInt(100);

        return (weather[seed % 4]);
    }
}
