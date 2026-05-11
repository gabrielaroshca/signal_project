package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Writes generated patient data to output files.
 */

public class FileOutputStrategy implements OutputStrategy {

    //Renamed field to lowerCamelCase
    private String baseDirectory;

    //Renamed field to lowerCamelCase
    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();

    /**
     * Constructs a FileOutputStrategy with the specified base directory.
     * 
     * @param baseDirectory the directory where output files will be written
     */
    //Renamed "fileOutputStrategy" to UpperCamelCase
    public FileOutputStrategy(String baseDirectory) {

        this.baseDirectory = baseDirectory;
    }

    /**
     * Writes generated patient data to a file associated with the data label.
     * @param patientId the ID of the patient 
     * @param timestamp the timestamp of the generated data 
     * @param label the category of health data being written
     * @param data the generated patient data value
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the filePath variable
        //Renamed variable to lowerCamelCase
        String filePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}