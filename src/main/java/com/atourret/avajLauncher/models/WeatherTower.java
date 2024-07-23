package com.atourret.avajLauncher.models;

import com.atourret.avajLauncher.services.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        super.conditionChanged();
    }
}
