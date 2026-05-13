package com.alerts.decorator;

import com.alerts.Alert;

/**
 * Base decorator class for alerts.
 *
 * This class wraps an Alert object and allows
 * extra information or behavior to be added.
 */
public class AlertDecorator {

    protected Alert alert;

    /**
     * Creates an AlertDecorator.
     *
     * @param alert the alert being decorated
     */
    public AlertDecorator(Alert alert) {
        this.alert = alert;
    }

    /**
     * Returns the patient ID from the alert.
     *
     * @return the patient ID
     */
    public String getPatientId() {
        return alert.getPatientId();
    }

    /**
     * Returns the alert condition.
     *
     * @return the alert condition
     */
    public String getCondition() {
        return alert.getCondition();
    }

    /**
     * Returns the alert timestamp.
     *
     * @return the timestamp
     */
    public long getTimestamp() {
        return alert.getTimestamp();
    }
}