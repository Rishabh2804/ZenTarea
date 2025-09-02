package spring.practice.zentarea.data.service.analytics;

import spring.practice.zentarea.model.analytics.TaskAnalytics;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TaskAnalyticsService {

    /**
     * Store task analytics data
     */
    TaskAnalytics saveTaskAnalytics(TaskAnalytics taskAnalytics);

    /**
     * Get tasks by status
     */
    List<TaskAnalytics> getTasksByStatus(String status);

    /**
     * Get tasks by priority
     */
    List<TaskAnalytics> getTasksByPriority(String priority);

    /**
     * Get tasks completed within date range
     */
    List<TaskAnalytics> getTasksCompletedBetweenDates(Date startDate, Date endDate);

    /**
     * Get tasks that took more than specified days to complete
     */
    List<TaskAnalytics> getTasksTakingMoreThanDays(Integer days);

    /**
     * Get average completion time by priority
     */
    Map<String, Double> getAverageCompletionTimeByPriority();

    /**
     * Get task count by status
     */
    Map<String, Long> getTaskCountByStatus();
}