package com.alerts;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages and routes alerts to medical staff.
 * Stores all generated alerts in the system.
 */
public class AlertManager {

    private List<Alert> alerts;

    /**
     * Creates a new AlertManager.
     */
    public AlertManager() {
        this.alerts = new ArrayList<>();
    }

    /**
     * Dispatches an alert and stores it.
     *
     * @param alert the alert to dispatch
     */
    public void dispatchAlert(Alert alert) {
        alerts.add(alert);

        System.out.println("ALERT -> Patient ID: " + alert.getPatientId() + ", Condition: " + alert.getCondition() + ", Timestamp: " + alert.getTimestamp());
    }

    /**
     * Returns all alerts in the system.
     *
     * @return list of alerts
     */
    public List<Alert> getAlerts() {
        return alerts;
    }
}
