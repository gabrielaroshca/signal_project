package com.alerts;

import java.util.ArrayList;
import java.util.List;

import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * Monitors patient records and generates alerts when abnormal
 * health conditions are detected.
 *
 * This class checks:
 * - blood pressure
 * - oxygen saturation
 * - cholesterol levels
 * - ECG peaks
 * - combined low blood pressure and low oxygen saturation
 */
public class AlertGenerator {

    /**
     * Access to stored patient data.
     */
    private DataStorage dataStorage;

    /**
     * Handles alert storage and dispatching.
     */
    private AlertManager alertManager;

    /**
     * Creates an AlertGenerator with a new AlertManager.
     *
     * @param dataStorage the patient data storage system
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
        this.alertManager = new AlertManager();
    }

    /**
     * Creates an AlertGenerator with a custom AlertManager.
     *
     * @param dataStorage  the patient data storage system
     * @param alertManager the alert manager used to dispatch alerts
     */
    public AlertGenerator(
            DataStorage dataStorage,
            AlertManager alertManager) {

        this.dataStorage = dataStorage;
        this.alertManager = alertManager;
    }

    /**
     * Evaluates a patient's records for abnormal values.
     *
     * @param patient the patient being evaluated
     */
    public void evaluateData(Patient patient) {

        List<Double> systolicValues = new ArrayList<>();
        List<Double> diastolicValues = new ArrayList<>();
        List<Double> saturationValues = new ArrayList<>();
        List<Double> ecgValues = new ArrayList<>();

        boolean lowSystolicDetected = false;
        boolean lowSaturationDetected = false;

        for (PatientRecord record : patient.getRecords(0, Long.MAX_VALUE)) {

            String type = record.getRecordType();
            double value = record.getMeasurementValue();

            // Check systolic pressure values
            if (type.equals("SystolicPressure")) {

                systolicValues.add(value);

                if (value > 180) {

                    triggerAlert(new Alert(
                            String.valueOf(patient.getPatientId()),
                            "Critical high systolic pressure",
                            record.getTimestamp()));
                }

                if (value < 90) {

                    lowSystolicDetected = true;

                    triggerAlert(new Alert(
                            String.valueOf(patient.getPatientId()),
                            "Critical low systolic pressure",
                            record.getTimestamp()));
                }
            }

            // Check diastolic pressure values
            if (type.equals("DiastolicPressure")) {

                diastolicValues.add(value);

                if (value > 120) {

                    triggerAlert(new Alert(
                            String.valueOf(patient.getPatientId()),
                            "Critical high diastolic pressure",
                            record.getTimestamp()));
                }

                if (value < 60) {

                    triggerAlert(new Alert(
                            String.valueOf(patient.getPatientId()),
                            "Critical low diastolic pressure",
                            record.getTimestamp()));
                }
            }

            // Check oxygen saturation values
            if (type.equals("Saturation")) {

                saturationValues.add(value);

                if (value < 92) {

                    lowSaturationDetected = true;

                    triggerAlert(new Alert(
                            String.valueOf(patient.getPatientId()),
                            "Low oxygen saturation",
                            record.getTimestamp()));
                }

                // Check if oxygen saturation dropped by 5 or more
                if (saturationValues.size() >= 2) {

                    double previous = saturationValues.get(
                            saturationValues.size() - 2);

                    if (previous - value >= 5) {

                        triggerAlert(new Alert(
                                String.valueOf(patient.getPatientId()),
                                "Rapid oxygen saturation drop detected",
                                record.getTimestamp()));
                    }
                }
            }

            // Check ECG values for abnormal peaks
            if (type.equals("ECG")) {

                ecgValues.add(value);

                /*
                 * Use the current average as a simple sliding window.
                 * If the new ECG value is much higher than the average,
                 * an alert is triggered.
                 */
                if (ecgValues.size() > 1) {

                    double average = calculateAverage(ecgValues);

                    if (value > average * 1.5) {

                        triggerAlert(new Alert(
                                String.valueOf(patient.getPatientId()),
                                "Abnormal ECG peak detected",
                                record.getTimestamp()));
                    }
                }
            }

            // Check manual triggered alerts from the generator
            if (type.equals("Alert") && value == 1.0) {

                triggerAlert(new Alert(
                        String.valueOf(patient.getPatientId()),
                        "Manual alert triggered",
                        record.getTimestamp()));
            }

            // Check cholesterol values
            if (type.equals("Cholesterol") && value > 200) {

                triggerAlert(new Alert(
                        String.valueOf(patient.getPatientId()),
                        "High cholesterol",
                        record.getTimestamp()));
            }
        }

        // Combined alert: low systolic pressure and low oxygen saturation
        if (lowSystolicDetected && lowSaturationDetected) {

            triggerAlert(new Alert(
                    String.valueOf(patient.getPatientId()),
                    "Hypotensive hypoxemia detected",
                    System.currentTimeMillis()));
        }

        // Check systolic pressure trends
        checkTrend(systolicValues, patient, "Systolic");

        // Check diastolic pressure trends
        checkTrend(diastolicValues, patient, "Diastolic");
    }

    /**
     * Calculates the average value from a list.
     *
     * @param values the values to average
     * @return the average value
     */
    private double calculateAverage(List<Double> values) {

        double sum = 0;

        for (double value : values) {
            sum += value;
        }

        return sum / values.size();
    }

    /**
     * Checks if values show a strong increasing
     * or decreasing trend.
     *
     * @param values  the list of recorded values
     * @param patient the patient being checked
     * @param type    the measurement type
     */
    private void checkTrend(
            List<Double> values,
            Patient patient,
            String type) {

        if (values.size() < 3) {
            return;
        }

        for (int i = 0; i < values.size() - 2; i++) {

            double first = values.get(i);
            double second = values.get(i + 1);
            double third = values.get(i + 2);

            // Increasing trend
            if ((second - first > 10)
                    && (third - second > 10)) {

                triggerAlert(new Alert(
                        String.valueOf(patient.getPatientId()),
                        type + " increasing trend detected",
                        System.currentTimeMillis()));
            }

            // Decreasing trend
            if ((first - second > 10)
                    && (second - third > 10)) {

                triggerAlert(new Alert(
                        String.valueOf(patient.getPatientId()),
                        type + " decreasing trend detected",
                        System.currentTimeMillis()));
            }
        }
    }

    /**
     * Sends an alert to the AlertManager.
     *
     * @param alert the alert to dispatch
     */
    private void triggerAlert(Alert alert) {
        alertManager.dispatchAlert(alert);
    }

    /**
     * Returns the AlertManager used by this generator.
     *
     * @return the alert manager
     */
    public AlertManager getAlertManager() {
        return alertManager;
    }
}