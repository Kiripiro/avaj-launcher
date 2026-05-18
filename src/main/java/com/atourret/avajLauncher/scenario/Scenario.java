package com.atourret.avajLauncher.scenario;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
    private PrintWriter writer;
    private boolean logToConsole = true;

    public Scenario() {
        this.simulations = 0;
        weatherTower = new WeatherTower();
    }

    public static Scenario getInstance() {
        if (instance == null)
            instance = new Scenario();
        return instance;
    }

    public void setSimulations(String simulations) throws InvalidScenarioException {
        this.simulations = Validator.validateSimulations(simulations);
    }

    public void log(String message) {
        String fileMessage = stripAnsi(message);
        if (writer != null) {
            writer.println(fileMessage);
            writer.flush();
        }
        if (logToConsole) {
            System.out.println(message);
        }
    }

    private String stripAnsi(String message) {
        return message.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    public int getSimulations() {
        return this.simulations;
    }

    public void addAircraft(String[] infos) throws InvalidScenarioException {
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
    }

    public void start() {
        try {
            writer = new PrintWriter("simulation.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }

        logToConsole = !"false".equalsIgnoreCase(System.getProperty("avaj.console", "true"));

        for (Flyable aircraft : aircrafts) {
            aircraft.registerTower(weatherTower);
        }
        int currentSimulation = 1;
        while (simulations-- > 0) {
            String line = "\n\u001B[32m\t== Simulation #" + (currentSimulation) + " ==\u001B[0m";
            log(line);
            weatherTower.changeWeather();
            currentSimulation++;
        }
        writer.close();
    }
}
