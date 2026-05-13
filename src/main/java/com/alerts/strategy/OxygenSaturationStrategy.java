package com.alerts.strategy;

import com.alerts.Alert;
import com.alerts.AlertManager;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * Strategy for monitoring oxygen saturation values.
 */
public class OxygenSaturationStrategy
        implements AlertStrategy {

    /**
     * Checks oxygen saturation readings for
     * abnormal values.
     *
     * @param patient the patient being checked
     * @param alertManager the alert manager
     */
    @Override
    public void checkAlert(Patient patient, AlertManager alertManager) {

        for (PatientRecord record : patient.getRecords(0, Long.MAX_VALUE)) {

            String type = record.getRecordType();
            double value = record.getMeasurementValue();

            if (type.equals("Saturation")) {

                if (value < 92) {

                    alertManager.dispatchAlert(
                            new Alert(
                                    String.valueOf(
                                            patient.getPatientId()), "Low oxygen saturation", record.getTimestamp()));
                }
            }
        }
    }
}