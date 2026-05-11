package com.data_management;

/**
 * Represents one time-stamped patient vital record.
 */
public class PatientData {

    private int patientId;
    private String recordType;
    private double measurementValue;
    private long timestamp;
    private int version;

    public PatientData(int patientId, String recordType, double measurementValue, long timestamp) {
        this.patientId = patientId;
        this.recordType = recordType;
        this.measurementValue = measurementValue;
        this.timestamp = timestamp;
        this.version = 1;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getRecordType() {
        return recordType;
    }

    public double getMeasurementValue() {
        return measurementValue;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getVersion() {
        return version;
    }
}