package com.identification;

/**
 * Manages patient identity validation and handles mismatch cases.
 * This class checks whether incoming patient IDs match hospital records.
 */
public class IdentityManager {

    private PatientIdentifier patientIdentifier;

    /**
     * Creates an IdentityManager object.
     *
     * @param patientIdentifier the patient identifier used for matching patients
     */
    public IdentityManager(PatientIdentifier patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
    }

    /**
     * Validates whether a patient ID exists in the hospital records.
     *
     * @param patientId the patient ID to validate
     * @return true if the patient exists, otherwise false
     */
    public boolean validatePatient(int patientId) {

        HospitalPatient patient = patientIdentifier.matchPatient(patientId);

        return patient != null;
    }

    /**
     * Handles cases where no matching patient record is found.
     *
     * @param patientId the unmatched patient ID
     */
    public void handleMismatch(int patientId) {

        System.out.println(
                "No hospital record found for patient ID: " + patientId);
    }
}