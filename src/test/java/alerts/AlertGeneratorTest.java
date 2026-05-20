package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.alerts.AlertGenerator;
import com.alerts.AlertManager;
import com.data_management.DataStorage;
import com.data_management.Patient;

/**
 * Tests the AlertGenerator class.
 *
 * These tests check that alerts are correctly created
 * when abnormal patient values are detected.
 */
public class AlertGeneratorTest {

    /**
     * Tests that a critical high systolic pressure value
     * creates an alert.
     *
     * Example:
     * 190 > 180
     */
    @Test
    public void testCriticalHighSystolicPressureTriggersAlert() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(190.0, "SystolicPressure", 1000L);

        generator.evaluateData(patient);

        assertEquals(1, alertManager.getAlerts().size());
        assertEquals("Critical high systolic pressure", alertManager.getAlerts().get(0).getCondition());
    }

    /**
     * Tests that a critical low systolic pressure value
     * creates an alert.
     *
     * Example:
     * 85 < 90
     */
    @Test
    public void testCriticalLowSystolicPressureTriggersAlert() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(85.0, "SystolicPressure", 1000L);

        generator.evaluateData(patient);

        assertEquals(1, alertManager.getAlerts().size());
        assertEquals("Critical low systolic pressure", alertManager.getAlerts().get(0).getCondition());
    }

    /**
     * Tests that an increasing blood pressure trend
     * creates an alert.
     *
     * Example:
     * 120 -> 135 -> 150
     */
    @Test
    public void testIncreasingBloodPressureTrendTriggersAlert() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(120.0, "SystolicPressure", 1000L);
        patient.addRecord(135.0, "SystolicPressure", 2000L);
        patient.addRecord(150.0, "SystolicPressure", 3000L);

        generator.evaluateData(patient);

        assertEquals(1, alertManager.getAlerts().size());
        assertEquals("Systolic increasing trend detected", alertManager.getAlerts().get(0).getCondition());
    }

    /**
     * Tests that a decreasing blood pressure trend
     * creates an alert.
     *
     * Example:
     * 150 -> 135 -> 120
     */
    @Test
    public void testDecreasingBloodPressureTrendTriggersAlert() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(150.0, "SystolicPressure", 1000L);
        patient.addRecord(135.0, "SystolicPressure", 2000L);
        patient.addRecord(120.0, "SystolicPressure", 3000L);

        generator.evaluateData(patient);

        assertEquals(1, alertManager.getAlerts().size());
        assertEquals("Systolic decreasing trend detected", alertManager.getAlerts().get(0).getCondition());
    }

    /**
     * Tests that low oxygen saturation
     * creates an alert.
     *
     * Example:
     * 89 < 92
     */
    @Test
    public void testLowSaturationTriggersAlert() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(89.0, "Saturation", 1000L);

        generator.evaluateData(patient);

        assertEquals(1, alertManager.getAlerts().size());
        assertEquals("Low oxygen saturation", alertManager.getAlerts().get(0).getCondition());
    }

    /**
     * Tests that a rapid oxygen saturation drop
     * creates an alert.
     *
     * Example:
     * 98 -> 92 drops by 6
     */
    @Test
    public void testRapidSaturationDropTriggersAlert() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(98.0, "Saturation", 1000L);
        patient.addRecord(92.0, "Saturation", 2000L);

        generator.evaluateData(patient);

        assertEquals(1, alertManager.getAlerts().size());
        assertEquals("Rapid oxygen saturation drop detected", alertManager.getAlerts().get(0).getCondition());
    }

    /**
     * Tests that low systolic pressure and low oxygen
     * saturation together create a combined alert.
     *
     * Example:
     * systolic = 85 and saturation = 89
     */
    @Test
    public void testHypotensiveHypoxemiaTriggersAlert() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(85.0, "SystolicPressure", 1000L);
        patient.addRecord(89.0, "Saturation", 2000L);

        generator.evaluateData(patient);

        assertEquals(3, alertManager.getAlerts().size());
        assertEquals("Hypotensive hypoxemia detected", alertManager.getAlerts().get(2).getCondition());
    }

    /**
     * Tests that an abnormal ECG peak
     * creates an alert.
     *
     * Example:
     * 1.0, 1.1, then 3.0 as a peak
     */
    @Test
    public void testAbnormalECGPeakTriggersAlert() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(1.0, "ECG", 1000L);
        patient.addRecord(1.1, "ECG", 2000L);
        patient.addRecord(3.0, "ECG", 3000L);

        generator.evaluateData(patient);

        assertEquals(1, alertManager.getAlerts().size());
        assertEquals("Abnormal ECG peak detected", alertManager.getAlerts().get(0).getCondition());
    }

    /**
     * Tests that a manual alert record
     * creates an alert.
     *
     * Example:
     * Alert value = 1.0
     */
    @Test
    public void testManualAlertTriggered() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(1.0, "Alert", 1000L);

        generator.evaluateData(patient);

        assertEquals(1, alertManager.getAlerts().size());
        assertEquals("Manual alert triggered", alertManager.getAlerts().get(0).getCondition());
    }

    @Test
    public void testCriticalHighDiastolicPressureTriggersAlert() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(125.0, "DiastolicPressure", 1000L);

        generator.evaluateData(patient);

        assertEquals(1, alertManager.getAlerts().size());
        assertEquals("Critical high diastolic pressure", alertManager.getAlerts().get(0).getCondition());
    }

    @Test
    public void testCriticalLowDiastolicPressureTriggersAlert() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(55.0, "DiastolicPressure", 1000L);

        generator.evaluateData(patient);

        assertEquals(1, alertManager.getAlerts().size());
        assertEquals("Critical low diastolic pressure", alertManager.getAlerts().get(0).getCondition());
    }

    @Test
    public void testBorderlineSystolicTrendDoesNotTriggerAlert() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(120.0, "SystolicPressure", 1000L);
        patient.addRecord(130.0, "SystolicPressure", 2000L);
        patient.addRecord(140.0, "SystolicPressure", 3000L);

        generator.evaluateData(patient);

        assertEquals(0, alertManager.getAlerts().size());
    }

    @Test
    public void testRapidSaturationDropOutsideTenMinutesDoesNotTriggerAlert() {
        DataStorage storage = DataStorage.getInstance();
        AlertManager alertManager = new AlertManager();
        AlertGenerator generator = new AlertGenerator(storage, alertManager);

        Patient patient = new Patient(1);
        patient.addRecord(98.0, "Saturation", 1000L);
        patient.addRecord(92.0, "Saturation", 700001L);

        generator.evaluateData(patient);

        assertEquals(0, alertManager.getAlerts().size());
    }
}