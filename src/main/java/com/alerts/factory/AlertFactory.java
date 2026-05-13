package com.alerts.factory;

import com.alerts.Alert;

/**
 * Factory interface used to create alerts.
 *
 * Different alert factories implement this interface
 * to generate specific types of alerts.
 */
public interface AlertFactory {

    /**
     * Creates an alert.
     *
     * @param patientId the patient ID
     * @param timestamp the alert timestamp
     * @return the created alert
     */
    Alert createAlert(String patientId, long timestamp);
}