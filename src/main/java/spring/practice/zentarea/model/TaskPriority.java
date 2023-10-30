package spring.practice.zentarea.model;

public enum TaskPriority {
    HIGH(101, 1000),
    MEDIUM(11, 100),
    LOW(0, 10);

    private final int minRange;

    public int getMinRange() {
        return minRange;
    }

    private final int maxRange;

    public int getMaxRange() {
        return maxRange;
    }

    public boolean isInRange(int value) {
        return value >= minRange && value <= maxRange;
    }

    TaskPriority(int minRange, int maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public static TaskPriority resolve(Object priority) {
        if (priority instanceof TaskPriority) return (TaskPriority) priority;
        else if (priority instanceof Integer) return resolve(priority.toString());
        else if (priority instanceof String) return resolve((String) priority);
        else throw new IllegalArgumentException("Invalid priority type");
    }

    private static TaskPriority resolve(String source) {

        try {
            // First, try to parse the source as an integer
            int priorityValue = Integer.parseInt(source);

            // Check the range to determine the TaskPriority
            if (LOW.isInRange(priorityValue)) return LOW;
            else if (MEDIUM.isInRange(priorityValue)) return MEDIUM;
            else return HIGH;

        } catch (NumberFormatException e) {
            // If parsing as an integer fails, try to match it with enum names
            return TaskPriority.valueOf(source.toUpperCase());
        }
    }

    @Override
    public String toString() {
        return this.name();
    }
}
