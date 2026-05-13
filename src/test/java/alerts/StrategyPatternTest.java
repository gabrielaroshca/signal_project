package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.alerts.AlertManager;
import com.alerts.strategy.BloodPressureStrategy;
import com.alerts.strategy.HeartRateStrategy;
import com.alerts.strategy.OxygenSaturationStrategy;
import com.data_management.Patient;

/**
 * Tests strategy pattern implementations.
 */
public class StrategyPatternTest {

    /**
     * Tests abnormal heart rate strategy.
     */
    @Test
    public void testHeartRateStrategy() {

        AlertManager manager = new AlertManager();

        HeartRateStrategy strategy = new HeartRateStrategy();

        Patient patient = new Patient(1);

        patient.addRecord(130.0, "HeartRate", 1000L);

        strategy.checkAlert(patient, manager);

        assertEquals(1, manager.getAlerts().size());
    }

    /**
     * Tests low oxygen strategy.
     */
    @Test
    public void testOxygenStrategy() {

        AlertManager manager = new AlertManager();

        OxygenSaturationStrategy strategy = new OxygenSaturationStrategy();

        Patient patient = new Patient(1);

        patient.addRecord(89.0, "Saturation", 1000L);

        strategy.checkAlert(patient, manager);

        assertEquals(1, manager.getAlerts().size());
    }

    /**
     * Tests blood pressure strategy.
     */
    @Test
    public void testBloodPressureStrategy() {

        AlertManager manager = new AlertManager();

        BloodPressureStrategy strategy = new BloodPressureStrategy();

        Patient patient = new Patient(1);

        patient.addRecord(190.0, "SystolicPressure", 1000L);

        strategy.checkAlert(patient, manager);

        assertEquals(1, manager.getAlerts().size());
    }
}