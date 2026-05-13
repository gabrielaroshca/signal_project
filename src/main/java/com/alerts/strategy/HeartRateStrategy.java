package com.alerts.strategy;

import com.alerts.Alert;
import com.alerts.AlertManager;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * Strategy for monitoring heart rate values.
 */
public class HeartRateStrategy
        implements AlertStrategy {

    /**
     * Checks heart rate readings for
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

            if (type.equals("HeartRate")) {

                if (value < 50 || value > 120) {

                    alertManager.dispatchAlert(
                            new Alert(
                                    String.valueOf(
                                            patient.getPatientId()), "Abnormal heart rate detected", record.getTimestamp()));
                }
            }
        }
    }
}