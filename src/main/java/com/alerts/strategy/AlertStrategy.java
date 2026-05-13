package com.alerts.strategy;

import com.alerts.AlertManager;
import com.data_management.Patient;

/**
 * Strategy interface for alert checking algorithms.
 *
 * Different monitoring strategies implement this
 * interface to evaluate patient health data.
 */
public interface AlertStrategy {

    /**
     * Checks patient data and triggers alerts
     * when abnormal conditions are detected.
     *
     * @param patient the patient being evaluated
     * @param alertManager the alert manager
     */
    void checkAlert(Patient patient, AlertManager alertManager);
}