package com.atourret.avajLauncher.models;

import java.util.HashMap;
import java.util.Map;

public class Baloon extends Aircraft {

    public Baloon(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
        super.setType("Baloon");
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 2);
                coordinates.setHeight(coordinates.getHeight() + 4);
                break;
            case "RAIN":
                coordinates.setHeight(coordinates.getHeight() - 5);
                break;
            case "FOG":
                coordinates.setHeight(coordinates.getHeight() - 3);
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 15);
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
        messages.put("SUN", "Baloon here! Floating like a lazy cloud under the sunny skies!");
        messages.put("RAIN", "Baloon reporting! Drip drop, I'm turning into a water balloon!");
        messages.put("FOG", "Baloon checking in! Help! I'm lost in the fog, feels like a spooky ghost town!");
        messages.put("SNOW", "Baloon enduring the cold! I'm a snowball with strings, brrr!");
        return messages;
    }
}
