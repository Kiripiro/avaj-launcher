package com.atourret.avajLauncher.scenario;

import java.util.ArrayList;

import com.atourret.avajLauncher.exceptions.InvalidScenarioException;
import com.atourret.avajLauncher.interfaces.Flyable;
import com.atourret.avajLauncher.models.AircraftFactory;
import com.atourret.avajLauncher.models.Coordinates;
import com.atourret.avajLauncher.models.WeatherTower;
import com.atourret.avajLauncher.parser.Validator;

public class Scenario {
    private static Scenario instance = null;
    private int simulations;
    private WeatherTower weatherTower;
    private ArrayList<Flyable> aircrafts = new ArrayList<>();

    public Scenario() {
        this.simulations = 0;
        weatherTower = new WeatherTower();
    }

    public static Scenario getInstance() {
        if (instance == null)
            instance = new Scenario();
        return instance;
    }

    public void setSimulations(String simulations) {
        try {
            this.simulations = Validator.validateSimulations(simulations);
        } catch (InvalidScenarioException e) {
            System.err.println(e.getMessage());
        }
    }

    public int getSimulations() {
        return this.simulations;
    }

    public void addAircraft(String[] infos) throws InvalidScenarioException {
        try {
            Validator.validateAircraft(infos);

            int longitude = Integer.parseInt(infos[2]);
            int latitude = Integer.parseInt(infos[3]);
            int height = Integer.parseInt(infos[4]);

            Coordinates coordinates = new Coordinates(
                    longitude,
                    latitude,
                    height);

            Flyable newAircraft = AircraftFactory.newAircraft(
                    infos[0],
                    infos[1],
                    coordinates);
            aircrafts.add(newAircraft);
        } catch (InvalidScenarioException e) {
            System.err.println(e.getMessage());
        }
    }

    public void start() {
        for (Flyable aircraft : aircrafts) {
            aircraft.registerTower(weatherTower);
        }

        int currentSimulation = 1;
        while (simulations-- > 0) {
            System.out.println("\n\u001B[32m\t== Simulation #" + (currentSimulation) + " ==\u001B[0m");
            weatherTower.changeWeather();
            currentSimulation++;
        }
    }
}
