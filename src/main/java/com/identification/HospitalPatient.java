package com.identification;

/**
 * Represents patient information stored in the hospital database.
 * This class stores identifying information and medical history
 * used by the patient identification system.
 */
public class HospitalPatient {

    private int patientId;
    private String patientName;
    private String medicalHistory;

    /**
     * Creates a HospitalPatient object.
     *
     * @param patientId the unique patient ID
     * @param patientName the patient's name
     * @param medicalHistory the patient's medical history
     */
    public HospitalPatient(
            int patientId,
            String patientName,
            String medicalHistory) {

        this.patientId = patientId;
        this.patientName = patientName;
        this.medicalHistory = medicalHistory;
    }

    /**
     * Returns the patient ID.
     *
     * @return the patient ID
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * Returns the patient name.
     *
     * @return the patient name
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Returns the patient's medical history.
     *
     * @return the medical history
     */
    public String getMedicalHistory() {
        return medicalHistory;
    }
}