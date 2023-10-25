package spring.practice.zentarea.model;

public enum TaskPriority {
    HIGH(101, Integer.MAX_VALUE),
    MEDIUM(11, 100),
    LOW(0, 10);

    private final int minRange;
    private final int maxRange;

    public boolean isInRange(int value) {
        return value >= minRange && value <= maxRange;
    }

    TaskPriority(int minRange, int maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public static TaskPriority parse(String source) {
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
}
