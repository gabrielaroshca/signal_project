package com.alerts.strategy;

import com.alerts.Alert;
import com.alerts.AlertManager;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * Strategy for monitoring blood pressure values.
 */
public class BloodPressureStrategy
        implements AlertStrategy {

    /**
     * Checks blood pressure readings for
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

            if (type.equals("SystolicPressure")) {

                if (value > 180) {

                    alertManager.dispatchAlert(
                            new Alert(
                                    String.valueOf(
                                            patient.getPatientId()), "Critical high systolic pressure", record.getTimestamp()));
                }

                if (value < 90) {

                    alertManager.dispatchAlert(
                            new Alert(
                                    String.valueOf(
                                            patient.getPatientId()), "Critical low systolic pressure", record.getTimestamp()));
                }
            }
        }
    }
}