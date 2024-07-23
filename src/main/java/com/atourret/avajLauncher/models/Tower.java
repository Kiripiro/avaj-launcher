package com.atourret.avajLauncher.models;

import java.util.ArrayList;
import java.util.List;

import com.atourret.avajLauncher.interfaces.Flyable;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        logTowerMessage("Tower says: ",
                flyable.getType()
                        + "#" + flyable.getName()
                        + "(" + flyable.getId()
                        + ") registered to weather tower.");
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        logTowerMessage("Tower says: ",
                flyable.getType()
                        + "#" + flyable.getName()
                        + "(" + flyable.getId()
                        + ") unregistered from weather tower.");
        observers.remove(flyable);
    }

    protected void conditionChanged() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }

    private void logTowerMessage(String prefix, String message) {
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_RESET = "\u001B[0m";
        System.out.println(ANSI_BLUE + prefix + ANSI_RESET + message);
    }
}
