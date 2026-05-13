package com.data_management;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Reads patient data from simulator output files.
 *
 * This class:
 * - reads all .txt files from a directory
 * - parses the data from each line
 * - sends the data to DataStorage
 */
public class FileDataReader implements DataReader {

    /**
     * Directory containing simulator output files.
     */
    private String directoryPath;

    /**
     * Creates a FileDataReader object.
     *
     * @param directoryPath path to the output folder
     */
    public FileDataReader(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    /**
     * Reads all .txt files from the directory.
     *
     * @param dataStorage stores the parsed patient data
     * @throws IOException if the directory cannot be read
     */
    @Override
    public void readData(DataStorage dataStorage) throws IOException {

        Files.list(Path.of(directoryPath)).filter(path -> path.toString().endsWith(".txt")).forEach(path -> readFile(path, dataStorage));
    }

    /**
     * Reads one file line by line.
     *
     * @param filePath    path of the file
     * @param dataStorage stores the parsed data
     */
    private void readFile(Path filePath, DataStorage dataStorage) {

        try {

            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                parseLine(line, dataStorage);
            }

        } catch (IOException e) {

            System.err.println("Error reading file: " + filePath);
        }
    }

    /**
     * Parses one line of simulator data.
     *
     * Example line:
     * Patient ID: 37, Timestamp: 1778671186290,
     * Label: ECG, Data: -0.27
     *
     * @param line        raw data line
     * @param dataStorage stores parsed values
     */
    private void parseLine(String line, DataStorage dataStorage) {

        try {

            String[] parts = line.split(",");

            int patientId = Integer.parseInt(parts[0].replace("Patient ID:", "").trim());

            long timestamp = Long.parseLong(parts[1].replace("Timestamp:", "").trim());

            String label = parts[2].replace("Label:", "").trim();

            String dataText = parts[3].replace("Data:", "").replace("%", "").trim();

            double measurementValue = Double.parseDouble(dataText);

            dataStorage.addPatientData(patientId, measurementValue, label, timestamp);

        } catch (Exception e) {

            /*
             * Ignore invalid or broken lines.
             * This prevents the program from crashing
             * if the file contains bad data.
             */
        }
    }
}