package com.identification;

import java.util.List;

/**
 * Matches incoming patient IDs with hospital patient records.
 * This class is responsible for identifying the correct patient
 * from the hospital database.
 */
public class PatientIdentifier {

    private List<HospitalPatient> hospitalPatients;

    /**
     * Creates a PatientIdentifier object.
     *
     * @param hospitalPatients the list of hospital patients
     */
    public PatientIdentifier(List<HospitalPatient> hospitalPatients) {
        this.hospitalPatients = hospitalPatients;
    }

    /**
     * Matches a patient ID with a hospital patient record.
     *
     * @param patientId the patient ID to search for
     * @return the matching HospitalPatient object,
     *         or null if no match is found
     */
    public HospitalPatient matchPatient(int patientId) {

        for (HospitalPatient patient : hospitalPatients) {

            if (patient.getPatientId() == patientId) {
                return patient;
            }
        }

        return null;
    }
}