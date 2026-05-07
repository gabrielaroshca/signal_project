package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;
/**
 * Defines a contract for generating simulated patient health data.
 * Implementations produce specific types of patient data such as ECG, blood pressure, or blood saturation values.
 */
public interface PatientDataGenerator {
    /**
     * Generates health data for a patient and sends the output using the provided output strategy.
     * 
     * @param patientId the ID of the patient receiving generated data
     * @param outputStrategy the strategy used to output generated data
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
