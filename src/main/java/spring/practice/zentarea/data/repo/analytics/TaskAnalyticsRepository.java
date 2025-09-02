package spring.practice.zentarea.data.repo.analytics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.practice.zentarea.model.analytics.TaskAnalytics;

import java.util.Date;
import java.util.List;

@Repository("taskAnalyticsRepository")
public interface TaskAnalyticsRepository extends JpaRepository<TaskAnalytics, Long> {

    /**
     * Find tasks by status
     */
    List<TaskAnalytics> findByStatus(String status);

    /**
     * Find tasks by priority
     */
    List<TaskAnalytics> findByPriority(String priority);

    /**
     * Find tasks completed within a date range
     */
    @Query("SELECT ta FROM TaskAnalytics ta WHERE ta.completedDate BETWEEN :startDate AND :endDate")
    List<TaskAnalytics> findTasksCompletedBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * Find tasks that took more than specified days to complete
     */
    @Query("SELECT ta FROM TaskAnalytics ta WHERE ta.daysToComplete > :days")
    List<TaskAnalytics> findTasksTakingMoreThanDays(@Param("days") Integer days);

    /**
     * Get average completion time by priority
     */
    @Query("SELECT ta.priority, AVG(ta.daysToComplete) FROM TaskAnalytics ta WHERE ta.daysToComplete IS NOT NULL GROUP BY ta.priority")
    List<Object[]> getAverageCompletionTimeByPriority();

    /**
     * Count tasks by status
     */
    @Query("SELECT ta.status, COUNT(ta) FROM TaskAnalytics ta GROUP BY ta.status")
    List<Object[]> getTaskCountByStatus();
}