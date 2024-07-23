package com.atourret.avajLauncher.exceptions;

public class InvalidScenarioException extends Exception {
    public static final String baseMsg = "\u001B[31mScenario Error ! \u001B[0m";

    public InvalidScenarioException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return baseMsg + super.getMessage();
    }
}
