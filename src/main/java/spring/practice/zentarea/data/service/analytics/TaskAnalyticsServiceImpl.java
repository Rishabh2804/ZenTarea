package spring.practice.zentarea.data.service.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.practice.zentarea.data.repo.analytics.TaskAnalyticsRepository;
import spring.practice.zentarea.model.analytics.TaskAnalytics;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("taskAnalyticsService")
@Transactional
public class TaskAnalyticsServiceImpl implements TaskAnalyticsService {

    private final TaskAnalyticsRepository taskAnalyticsRepository;

    @Autowired
    public TaskAnalyticsServiceImpl(TaskAnalyticsRepository taskAnalyticsRepository) {
        this.taskAnalyticsRepository = taskAnalyticsRepository;
    }

    @Override
    public TaskAnalytics saveTaskAnalytics(TaskAnalytics taskAnalytics) {
        return taskAnalyticsRepository.save(taskAnalytics);
    }

    @Override
    public List<TaskAnalytics> getTasksByStatus(String status) {
        return taskAnalyticsRepository.findByStatus(status);
    }

    @Override
    public List<TaskAnalytics> getTasksByPriority(String priority) {
        return taskAnalyticsRepository.findByPriority(priority);
    }

    @Override
    public List<TaskAnalytics> getTasksCompletedBetweenDates(Date startDate, Date endDate) {
        return taskAnalyticsRepository.findTasksCompletedBetweenDates(startDate, endDate);
    }

    @Override
    public List<TaskAnalytics> getTasksTakingMoreThanDays(Integer days) {
        return taskAnalyticsRepository.findTasksTakingMoreThanDays(days);
    }

    @Override
    public Map<String, Double> getAverageCompletionTimeByPriority() {
        List<Object[]> results = taskAnalyticsRepository.getAverageCompletionTimeByPriority();
        Map<String, Double> averages = new HashMap<>();
        
        for (Object[] result : results) {
            String priority = (String) result[0];
            Double avgDays = (Double) result[1];
            averages.put(priority, avgDays);
        }
        
        return averages;
    }

    @Override
    public Map<String, Long> getTaskCountByStatus() {
        List<Object[]> results = taskAnalyticsRepository.getTaskCountByStatus();
        Map<String, Long> counts = new HashMap<>();
        
        for (Object[] result : results) {
            String status = (String) result[0];
            Long count = (Long) result[1];
            counts.put(status, count);
        }
        
        return counts;
    }
}