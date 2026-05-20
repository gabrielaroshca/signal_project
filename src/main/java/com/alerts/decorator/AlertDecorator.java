package com.alerts.decorator;

import com.alerts.Alert;

/**
 * Base decorator class for alerts.
 */
public class AlertDecorator extends Alert {

    protected Alert alert;

    /**
     * Creates an AlertDecorator.
     *
     * @param alert the alert being decorated
     */
    public AlertDecorator(Alert alert) {
        super(alert.getPatientId(), alert.getCondition(), alert.getTimestamp());
        this.alert = alert;
    }

    @Override
    public String getPatientId() {
        return alert.getPatientId();
    }

    @Override
    public String getCondition() {
        return alert.getCondition();
    }

    @Override
    public long getTimestamp() {
        return alert.getTimestamp();
    }
}