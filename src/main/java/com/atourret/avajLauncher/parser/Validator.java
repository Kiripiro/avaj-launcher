package com.atourret.avajLauncher.parser;

import com.atourret.avajLauncher.exceptions.InvalidScenarioException;

public class Validator {

    private static final String BALOON = "Baloon";
    private static final String JETPLANE = "JetPlane";
    private static final String HELICOPTER = "Helicopter";

    private static final String INVALID_SIMULATION_COUNT = "The number of simulations should be between 1 and 100.";
    private static final String INVALID_SIMULATION_FORMAT = "The number of simulations must be a valid integer.";
    private static final String INVALID_AIRCRAFT_FORMAT = "Invalid format: should be 'Type Name Longitude Latitude Height'";
    private static final String INVALID_AIRCRAFT_TYPE = "Invalid aircraft type: ";
    private static final String INVALID_NAME_FORMAT = "Invalid name: The name should be alphanumeric.";
    private static final String NON_NEGATIVE_COORDINATES = "Coordinates and height must be non-negative integers.";

    public static int validateSimulations(String simulations) throws InvalidScenarioException {
        try {
            int simulationsNb = Integer.parseInt(simulations);
            if (simulationsNb < 1 || simulationsNb > 100) {
                throw new InvalidScenarioException(INVALID_SIMULATION_COUNT);
            }
            return simulationsNb;
        } catch (NumberFormatException e) {
            throw new InvalidScenarioException(INVALID_SIMULATION_FORMAT);
        }
    }

    public static void validateAircraft(String[] infos) throws InvalidScenarioException {
        if (infos.length != 5) {
            throw new InvalidScenarioException(INVALID_AIRCRAFT_FORMAT);
        }

        validateAircraftType(infos[0]);
        validateAircraftName(infos[1]);
        validateCoordinates(infos[2], infos[3], infos[4]);
    }

    private static void validateAircraftType(String type) throws InvalidScenarioException {
        if (!type.equals(BALOON) && !type.equals(JETPLANE) && !type.equals(HELICOPTER)) {
            throw new InvalidScenarioException(INVALID_AIRCRAFT_TYPE + type);
        }
    }

    private static void validateAircraftName(String name) throws InvalidScenarioException {
        if (!name.matches("[A-Za-z0-9]+")) {
            throw new InvalidScenarioException(INVALID_NAME_FORMAT + ": " + name);
        }
    }

    private static void validateCoordinates(String lonStr, String latStr, String heightStr)
            throws InvalidScenarioException {
        int longitude = parseCoordinate(lonStr);
        int latitude = parseCoordinate(latStr);
        int height = parseCoordinate(heightStr);

        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new InvalidScenarioException(NON_NEGATIVE_COORDINATES);
        }
    }

    private static int parseCoordinate(String value) throws InvalidScenarioException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidScenarioException("Invalid coordinate value: " + value);
        }
    }
}
