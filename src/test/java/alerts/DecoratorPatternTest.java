package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.decorator.PriorityAlertDecorator;
import com.alerts.decorator.RepeatedAlertDecorator;

/**
 * Tests decorator pattern implementations.
 */
public class DecoratorPatternTest {

    /**
     * Tests priority alert decorator.
     */
    @Test
    public void testPriorityDecorator() {

        Alert alert = new Alert("1", "Critical alert", 1000L);

        PriorityAlertDecorator decorator = new PriorityAlertDecorator(alert, "HIGH");

        assertEquals("[HIGH] Critical alert", decorator.getCondition());
    }

    /**
     * Tests repeated alert decorator.
     */
    @Test
    public void testRepeatedDecorator() {

        Alert alert = new Alert("1", "Critical alert", 1000L);

        RepeatedAlertDecorator decorator = new RepeatedAlertDecorator(alert, 3);

        assertEquals("Critical alert repeated 3 times", decorator.getCondition());
    }
}