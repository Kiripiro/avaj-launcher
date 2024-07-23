package com.atourret.avajLauncher.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.atourret.avajLauncher.exceptions.InvalidScenarioException;
import com.atourret.avajLauncher.exceptions.ParsingErrorException;
import com.atourret.avajLauncher.scenario.Scenario;

public class Parser {

    private static final String SPLITTER = " ";
    private List<String> lines;

    private Parser() {
    }

    public static void parse(String fileName) throws IOException, ParsingErrorException {
        URL resource = Parser.class.getResource("/" + fileName);
        if (resource == null) {
            throw new ParsingErrorException("File not found: " + fileName);
        }

        try {
            Path path = Paths.get(resource.toURI());
            Parser parser = new Parser();
            parser.readLines(path);
            parser.parseAndValidate();
        } catch (URISyntaxException e) {
            throw new ParsingErrorException("Unable to convert the file path" + e.getMessage());
        }
    }

    private void readLines(Path path) throws IOException {
        lines = Files.lines(path).collect(Collectors.toList());
    }

    private void parseAndValidate() throws ParsingErrorException {
        if (lines.isEmpty()) {
            throw new ParsingErrorException("Input file is empty");
        }

        try {
            String simulations = parseFirstLine(lines.remove(0));
            Scenario.getInstance().setSimulations(simulations);

            for (String line : lines) {
                Scenario.getInstance().addAircraft(parseAircraft(line));
            }
        } catch (InvalidScenarioException e) {
            throw new ParsingErrorException(e.getMessage());
        }
    }

    private String parseFirstLine(String line) throws InvalidScenarioException {
        String[] content = line.split(SPLITTER);
        if (content.length != 1) {
            throw new InvalidScenarioException(
                    "Incorrect syntax, the first line should only contain one numerical word, such as '25'.");
        }
        return content[0];
    }

    private String[] parseAircraft(String parameters) throws InvalidScenarioException {
        String normalizedParameters = parameters.trim().replaceAll("\\s+", " ");
        String[] infos = normalizedParameters.split(SPLITTER);
        Validator.validateAircraft(infos);
        return infos;
    }
}
