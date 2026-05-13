package com.alerts.factory;

import com.alerts.Alert;

/**
 * Factory for ECG alerts.
 */
public class ECGAlertFactory implements AlertFactory {

    /**
     * Creates an ECG alert.
     *
     * @param patientId the patient ID
     * @param timestamp the alert timestamp
     * @return an ECG alert
     */
    @Override
    public Alert createAlert(String patientId, long timestamp) {

        return new Alert(patientId, "ECG abnormal peak detected", timestamp);
    }
}