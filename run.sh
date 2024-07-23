#!/bin/bash

# Find all .java files, and export them in the sources.txt file
find src/main/java/com/atourret/avajLauncher/ -name "*.java" > sources.txt

# Create output folders
mkdir -p build/classes & mkdir -p build/resources

# Copy the scenario file in the proper directory
cp src/main/resources/scenario.txt build/resources

# Compile the application.
javac -d build/classes @sources.txt

# Launch the application with the scenario file as argument.
java -classpath "build/classes:build/resources" src/main/java/com/atourret/avajLauncher/Main.java scenario.txt