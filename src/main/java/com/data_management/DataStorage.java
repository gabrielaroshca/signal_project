package com.data_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alerts.AlertGenerator;

/**
 * Manages storage and retrieval of patient data within a healthcare monitoring
 * system.
 *
 * This class serves as a repository for all patient records, organized by
 * patient IDs.
 *
 * This class also uses the Singleton pattern, which means only one shared
 * DataStorage object is created for the system.
 */
public class DataStorage {

    /**
     * Singleton instance of DataStorage.
     */
    private static DataStorage instance;

    /**
     * Stores patient objects indexed by their unique patient ID.
     */
    private Map<Integer, Patient> patientMap;

    /**
     * Constructs a new DataStorage object.
     *
     * This constructor is still public so existing unit tests and project code
     * can create separate storage objects when needed.
     */
    public DataStorage() {
        this.patientMap = new HashMap<>();
    }

    /**
     * Returns the shared DataStorage instance.
     *
     * If the instance does not exist yet, it is created.
     *
     * @return the singleton DataStorage instance
     */
    public static synchronized DataStorage getInstance() {

        if (instance == null) {
            instance = new DataStorage();
        }

        return instance;
    }

    /**
     * Adds or updates patient data in the storage.
     *
     * If the patient does not exist, a new Patient object is created and added to
     * the storage. Otherwise, the new data is added to the existing patient's
     * records.
     *
     * @param patientId        the unique identifier of the patient
     * @param measurementValue the value of the health metric being recorded
     * @param recordType       the type of record, e.g., "HeartRate",
     *                         "BloodPressure"
     * @param timestamp        the time at which the measurement was taken
     */
    public void addPatientData(int patientId, double measurementValue, String recordType, long timestamp) {

        Patient patient = patientMap.get(patientId);

        if (patient == null) {
            patient = new Patient(patientId);
            patientMap.put(patientId, patient);
        }

        patient.addRecord(measurementValue, recordType, timestamp);
    }

    /**
     * Retrieves a list of PatientRecord objects for a specific patient,
     * filtered by a time range.
     *
     * @param patientId the unique identifier of the patient
     * @param startTime the start of the time range
     * @param endTime   the end of the time range
     * @return a list of PatientRecord objects in the time range
     */
    public List<PatientRecord> getRecords(int patientId, long startTime, long endTime) {

        Patient patient = patientMap.get(patientId);

        if (patient != null) {
            return patient.getRecords(startTime, endTime);
        }

        return new ArrayList<>();
    }

    /**
     * Retrieves a collection of all patients stored in the data storage.
     *
     * @return a list of all patients
     */
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patientMap.values());
    }

    /**
     * Stores patient data in the storage system.
     *
     * @param patientId the patient ID
     * @param data      the patient data to store
     */
    public void storeData(int patientId, PatientData data) {

        addPatientData(patientId, data.getMeasurementValue(), data.getRecordType(), data.getTimestamp());
    }

    /**
     * Retrieves patient data in a time range.
     *
     * @param patientId the patient ID
     * @param startTime the start timestamp
     * @param endTime   the end timestamp
     * @return list of patient data
     */
    public List<PatientData> getPatientData(int patientId, long startTime, long endTime) {

        List<PatientRecord> records = getRecords(patientId, startTime, endTime);

        List<PatientData> patientDataList = new ArrayList<>();

        for (PatientRecord record : records) {

            PatientData patientData = new PatientData(record.getPatientId(), record.getRecordType(),
                    record.getMeasurementValue(), record.getTimestamp());

            patientDataList.add(patientData);
        }

        return patientDataList;
    }

    /**
     * Deletes old patient data.
     *
     * @param cutoffTimestamp records older than this timestamp should be deleted
     */
    public void deleteOldData(long cutoffTimestamp) {
        // Placeholder for deletion policy.
    }

    /**
     * Checks if a user role has access to patient data.
     *
     * @param userRole  the role of the user
     * @param patientId the patient ID
     * @return true if the user has access
     */
    public boolean hasAccess(String userRole, int patientId) {
        return true;
    }

    /**
     * Main method for testing DataStorage execution.
     *
     * This method uses the singleton DataStorage instance, retrieves example
     * records, and evaluates all stored patients for alerts.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        DataStorage storage = DataStorage.getInstance();

        List<PatientRecord> records = storage.getRecords(1, 1700000000000L, 1800000000000L);

        for (PatientRecord record : records) {

            System.out.println("Record for Patient ID: " + record.getPatientId() + ", Type: " + record.getRecordType()
                    + ", Data: " + record.getMeasurementValue() + ", Timestamp: " + record.getTimestamp());
        }

        AlertGenerator alertGenerator = new AlertGenerator(storage);

        for (Patient patient : storage.getAllPatients()) {
            alertGenerator.evaluateData(patient);
        }
    }
}