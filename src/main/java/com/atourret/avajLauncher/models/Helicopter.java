package com.atourret.avajLauncher.models;

import java.util.HashMap;
import java.util.Map;

public class Helicopter extends Aircraft {

    public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
        super.setType("Helicopter");
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                break;
            case "RAIN":
                coordinates.setLongitude(coordinates.getLongitude() + 5);
                break;
            case "FOG":
                coordinates.setLongitude(coordinates.getLongitude() + 1);
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 12);
                break;
            default:
                throw new IllegalArgumentException("Unknown weather type: " + weather);
        }
        System.out.println(getIdentifier() + getWeatherMessage(weather));

        if (coordinates.getHeight() <= 0) {
            coordinates.setHeight(0);
            System.out.println(getIdentifier() + "Landing... " + coordinates.toString());
            weatherTower.unregister(this);
        }
    }

    @Override
    protected Map<String, String> getWeatherMessages() {
        Map<String, String> messages = new HashMap<>();
        messages.put("SUN", "Helicopter here! Enjoying the sunny skies, feeling like a beach day up here!");
        messages.put("RAIN", "Helicopter reporting! Splish splash, dodging raindrops like a pro!");
        messages.put("FOG", "Helicopter checking in! This fog is so dense, I need a GPS to find my own rotors!");
        messages.put("SNOW", "Helicopter braving the storm! It's snowing harder than a Christmas miracle!");
        return messages;
    }
}
