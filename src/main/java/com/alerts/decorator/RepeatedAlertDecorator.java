package com.alerts.decorator;

import com.alerts.Alert;

/**
 * Adds repeated alert information to an alert.
 */
public class RepeatedAlertDecorator extends AlertDecorator {

    private int repeatCount;

    /**
     * Creates a repeated alert decorator.
     *
     * @param alert the alert being decorated
     * @param repeatCount number of times the alert repeats
     */
    public RepeatedAlertDecorator(Alert alert, int repeatCount) {

        super(alert);
        this.repeatCount = repeatCount;
    }

    /**
     * Returns the alert condition with repeat information.
     *
     * @return condition with repeat count
     */
    @Override
    public String getCondition() {
        return alert.getCondition() + " repeated " + repeatCount + " times";
    }

    /**
     * Returns the repeat count.
     *
     * @return repeat count
     */
    public int getRepeatCount() {
        return repeatCount;
    }
}