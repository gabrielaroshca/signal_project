package com.cardio_generator.outputs;

/**
 * Defines a strategy for outputting generated patient health data.
 * Implementations may send data to the console, files, TCP connections, or WebSocket streams.
 */
public interface OutputStrategy {

/**
 * Outputs generated patient health data.
 *
 * @param patientId the ID of the patient associated with the data
 * @param timestamp the timestamp when the data was generated
 * @param label the category of health data being output
 * @param data the generated patient data value
 */
    void output(int patientId, long timestamp, String label, String data);
}
