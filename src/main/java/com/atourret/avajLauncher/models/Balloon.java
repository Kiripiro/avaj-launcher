package com.atourret.avajLauncher.models;

import java.util.HashMap;
import java.util.Map;

import com.atourret.avajLauncher.scenario.Scenario;

public class Balloon extends Aircraft {

    public Balloon(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
        super.setType("Balloon");
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
        Scenario.getInstance().log(getIdentifier() + getWeatherMessage(weather));

        if (coordinates.getHeight() <= 0) {
            coordinates.setHeight(0);
            String aircraftName = getType() + "#" + getName() + "(" + getId() + ")";
            Scenario.getInstance().log(aircraftName + " landing.");
            weatherTower.unregister(this);
        }
    }

    @Override
    protected Map<String, String> getWeatherMessages() {
        Map<String, String> messages = new HashMap<>();
        messages.put("SUN", "Balloon here! Floating like a lazy cloud under the sunny skies!");
        messages.put("RAIN", "Balloon reporting! Drip drop, I'm turning into a water balloon!");
        messages.put("FOG", "Balloon checking in! Help! I'm lost in the fog, feels like a spooky ghost town!");
        messages.put("SNOW", "Balloon enduring the cold! I'm a snowball with strings, brrr!");
        return messages;
    }
}
