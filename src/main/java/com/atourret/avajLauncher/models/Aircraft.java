package com.atourret.avajLauncher.models;

import java.util.Map;

import com.atourret.avajLauncher.interfaces.Flyable;

public abstract class Aircraft implements Flyable {
    protected long id;
    protected String name;
    private String type;
    protected Coordinates coordinates;
    protected WeatherTower weatherTower;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinates;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return "\u001B[38;5;214m"
                + getType()
                + "#" + getName()
                + "(" + getId() + "): "
                + "\u001B[0m";
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
    }

    protected abstract Map<String, String> getWeatherMessages();

    protected String getWeatherMessage(String weather) {
        return getWeatherMessages().getOrDefault(weather, "Unknown weather condition.");
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", coordinates=" + coordinates +
                ", weatherTower=" + weatherTower +
                '}';
    }
}
