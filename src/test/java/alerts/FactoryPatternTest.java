package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.factory.AlertFactory;
import com.alerts.factory.BloodOxygenAlertFactory;
import com.alerts.factory.BloodPressureAlertFactory;
import com.alerts.factory.ECGAlertFactory;

/**
 * Tests factory pattern alert creation.
 */
public class FactoryPatternTest {

    /**
     * Tests blood pressure alert factory.
     */
    @Test
    public void testBloodPressureFactoryCreatesAlert() {

        AlertFactory factory = new BloodPressureAlertFactory();

        Alert alert = factory.createAlert("1", 1000L);

        assertEquals("Blood pressure alert", alert.getCondition());
    }

    /**
     * Tests blood oxygen alert factory.
     */
    @Test
    public void testBloodOxygenFactoryCreatesAlert() {

        AlertFactory factory = new BloodOxygenAlertFactory();

        Alert alert = factory.createAlert("1", 1000L);

        assertEquals("Low oxygen saturation", alert.getCondition());
    }

    /**
     * Tests ECG alert factory.
     */
    @Test
    public void testECGFactoryCreatesAlert() {

        AlertFactory factory = new ECGAlertFactory();

        Alert alert = factory.createAlert("1", 1000L);

        assertEquals("ECG abnormal peak detected", alert.getCondition());
    }
}