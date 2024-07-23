package com.atourret.avajLauncher.interfaces;

import com.atourret.avajLauncher.models.WeatherTower;

public interface Flyable {
    void updateConditions();

    void registerTower(WeatherTower p_tower);

    public abstract long getId();

    public abstract String getName();

    public abstract String getType();
}
