package com.alerts.decorator;

import com.alerts.Alert;

/**
 * Adds priority information to an alert.
 */
public class PriorityAlertDecorator extends AlertDecorator {

    private String priorityLevel;

    /**
     * Creates a priority alert decorator.
     *
     * @param alert the alert being decorated
     * @param priorityLevel the priority level
     */
    public PriorityAlertDecorator(Alert alert, String priorityLevel) {

        super(alert);
        this.priorityLevel = priorityLevel;
    }

    /**
     * Returns the alert condition with priority added.
     *
     * @return condition with priority level
     */
    @Override
    public String getCondition() {
        return "[" + priorityLevel + "] " + alert.getCondition();
    }

    /**
     * Returns the priority level.
     *
     * @return the priority level
     */
    public String getPriorityLevel() {
        return priorityLevel;
    }
}