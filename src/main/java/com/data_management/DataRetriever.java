package com.data_management;

import java.util.List;

/**
 * Handles retrieval of patient data for medical staff.
 */
public class DataRetriever {

    private DataStorage dataStorage;
    private String userRole;

    /**
     * Creates a DataRetriever.
     *
     * @param dataStorage the storage system
     * @param userRole the role of the user requesting data
     */
    public DataRetriever(DataStorage dataStorage, String userRole) {
        this.dataStorage = dataStorage;
        this.userRole = userRole;
    }

    /**
     * Retrieves patient data within a time range.
     *
     * @param patientId the patient ID
     * @param startTime the start timestamp
     * @param endTime the end timestamp
     * @return matching patient data
     */
    public List<PatientData> retrievePatientData(
            int patientId,
            long startTime,
            long endTime) {

        // Access control check
        if (!dataStorage.hasAccess(userRole, patientId)) {
            throw new SecurityException(
                    "Access denied for this patient data.");
        }

        return dataStorage.getPatientData(
                patientId,
                startTime,
                endTime);
    }
}