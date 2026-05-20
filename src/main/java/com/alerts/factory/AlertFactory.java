package com.alerts.factory;

import com.alerts.Alert;

/**
 * Abstract factory used to create alerts.
 */
public abstract class AlertFactory {

    /**
     * Creates an alert with a patient ID, condition, and timestamp.
     *
     * @param patientId the patient ID
     * @param condition the alert condition
     * @param timestamp the alert timestamp
     * @return the created alert
     */
    public abstract Alert createAlert(String patientId, String condition, long timestamp);
}