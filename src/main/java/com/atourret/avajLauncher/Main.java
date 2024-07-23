package com.atourret.avajLauncher;

import com.atourret.avajLauncher.exceptions.ParsingErrorException;
import com.atourret.avajLauncher.parser.Parser;
import com.atourret.avajLauncher.scenario.Scenario;

public class Main {
    public static void main(String[] args) {
        try {
            validateArgs(args);
            String fileName = args[0];
            Parser.parse(fileName);
            Scenario.getInstance().start();
        } catch (ParsingErrorException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void validateArgs(String[] args) throws ParsingErrorException {
        if (args.length != 1) {
            throw new ParsingErrorException("Un seul fichier doit être fourni.");
        }
    }
}
