package com.alerts.factory;

import com.alerts.Alert;

/**
 * Factory for oxygen saturation alerts.
 */
public class BloodOxygenAlertFactory implements AlertFactory {

    /**
     * Creates an oxygen saturation alert.
     *
     * @param patientId the patient ID
     * @param timestamp the alert timestamp
     * @return an oxygen saturation alert
     */
    @Override
    public Alert createAlert(String patientId, long timestamp) {

        return new Alert( patientId, "Low oxygen saturation", timestamp);
    }
}