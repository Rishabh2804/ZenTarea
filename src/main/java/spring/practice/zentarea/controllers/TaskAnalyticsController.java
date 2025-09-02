package spring.practice.zentarea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.zentarea.data.service.analytics.TaskAnalyticsService;
import spring.practice.zentarea.model.analytics.TaskAnalytics;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class TaskAnalyticsController {

    private final TaskAnalyticsService taskAnalyticsService;

    @Autowired
    public TaskAnalyticsController(TaskAnalyticsService taskAnalyticsService) {
        this.taskAnalyticsService = taskAnalyticsService;
    }

    /**
     * Store analytics data for a task
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<TaskAnalytics> createTaskAnalytics(@RequestBody TaskAnalytics taskAnalytics) {
        TaskAnalytics saved = taskAnalyticsService.saveTaskAnalytics(taskAnalytics);
        return ResponseEntity.ok(saved);
    }

    /**
     * Get tasks by status
     */
    @GetMapping(value = "/status/{status}", produces = "application/json")
    public ResponseEntity<List<TaskAnalytics>> getTasksByStatus(@PathVariable String status) {
        List<TaskAnalytics> tasks = taskAnalyticsService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Get tasks by priority
     */
    @GetMapping(value = "/priority/{priority}", produces = "application/json")
    public ResponseEntity<List<TaskAnalytics>> getTasksByPriority(@PathVariable String priority) {
        List<TaskAnalytics> tasks = taskAnalyticsService.getTasksByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Get tasks completed within date range
     */
    @GetMapping(value = "/completed", produces = "application/json")
    public ResponseEntity<List<TaskAnalytics>> getTasksCompletedBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<TaskAnalytics> tasks = taskAnalyticsService.getTasksCompletedBetweenDates(startDate, endDate);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Get tasks that took more than specified days to complete
     */
    @GetMapping(value = "/slow-tasks/{days}", produces = "application/json")
    public ResponseEntity<List<TaskAnalytics>> getTasksTakingMoreThanDays(@PathVariable Integer days) {
        List<TaskAnalytics> tasks = taskAnalyticsService.getTasksTakingMoreThanDays(days);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Get average completion time by priority
     */
    @GetMapping(value = "/avg-completion-time", produces = "application/json")
    public ResponseEntity<Map<String, Double>> getAverageCompletionTimeByPriority() {
        Map<String, Double> averages = taskAnalyticsService.getAverageCompletionTimeByPriority();
        return ResponseEntity.ok(averages);
    }

    /**
     * Get task count by status
     */
    @GetMapping(value = "/status-counts", produces = "application/json")
    public ResponseEntity<Map<String, Long>> getTaskCountByStatus() {
        Map<String, Long> counts = taskAnalyticsService.getTaskCountByStatus();
        return ResponseEntity.ok(counts);
    }
}