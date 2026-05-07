package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Generates alert events for patientes
 * 
 */
public class AlertGenerator implements PatientDataGenerator {

    public static final Random randomGenerator = new Random();

    //Renamed field to lowerCamelCase
    private boolean[] alertStates; // false = resolved, true = pressed

    /**
     * Constructs an AlertGenerator for the specified number of patientes.
     * 
     * @param patientCount
     */
    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1];
    }

    /**
     * Generates alert data for a patient.
     * 
     * @param patientID the ID of the patient.
     * @param outputStrategy the output strategy used to display generated data
     */
    @Override
    public void generate (int patientId, OutputStrategy outputStrategy) { //space added after generate
        try {
            if (alertStates[patientId]) {
                // 90% chance to resolve an active alert
                if (randomGenerator.nextDouble() < 0.9) { 
                    alertStates[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                // Renamed variable to lowerCamelCase
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                //Renamed to a more descriptive name
                double alertProbability = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = randomGenerator.nextDouble() < alertProbability;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
