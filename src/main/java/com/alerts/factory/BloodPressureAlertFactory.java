package com.alerts.factory;

import com.alerts.Alert;

/**
 * Factory for blood pressure alerts.
 */
public class BloodPressureAlertFactory implements AlertFactory {

    /**
     * Creates a blood pressure alert.
     *
     * @param patientId the patient ID
     * @param timestamp the alert timestamp
     * @return a blood pressure alert
     */
    @Override
    public Alert createAlert(String patientId, long timestamp) {

        return new Alert(patientId, "Blood pressure alert", timestamp);
    }
}