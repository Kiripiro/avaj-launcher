package com.atourret.avajLauncher.models;

import java.util.HashMap;
import java.util.Map;

public class JetPlane extends Aircraft {
    public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
        super.setType("JetPlane");
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates.setLatitude(coordinates.getLatitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                break;
            case "RAIN":
                coordinates.setLatitude(coordinates.getLatitude() + 5);
                break;
            case "FOG":
                coordinates.setLatitude(coordinates.getLatitude() + 1);
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 7);
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
        messages.put("SUN", "JetPlane here! I'm blazing through the sky like a rockstar in the sunshine!");
        messages.put("RAIN", "JetPlane reporting! Drip, drop, let's hope I don't flop in this rain!");
        messages.put("FOG", "JetPlane checking in! This fog is thicker than grandma's pea soup!");
        messages.put("SNOW", "JetPlane braving the cold! Brrr! Feels like I'm flying in a snow globe!");
        return messages;
    }
}
