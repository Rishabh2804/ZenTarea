package spring.practice.zentarea.data.service.analytics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import spring.practice.zentarea.model.analytics.TaskAnalytics;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for TaskAnalyticsService to ensure Redshift analytics functionality works
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TaskAnalyticsServiceTest {

    @Autowired
    private TaskAnalyticsService taskAnalyticsService;

    @Test
    public void contextLoads() {
        // This test ensures that the Spring context loads successfully
        // with the new Redshift configuration
        assertNotNull(taskAnalyticsService);
    }

    @Test
    public void testSaveAndRetrieveTaskAnalytics() {
        // Create a sample task analytics record
        TaskAnalytics analytics = new TaskAnalytics(
                1L, 
                "Test Task", 
                "COMPLETED", 
                "HIGH", 
                new Date(), 
                new Date(), 
                new Date(), 
                5, 
                3
        );

        // Save the analytics record
        TaskAnalytics saved = taskAnalyticsService.saveTaskAnalytics(analytics);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Test Task", saved.getTitle());
        assertEquals("COMPLETED", saved.getStatus());
        assertEquals("HIGH", saved.getPriority());
        assertEquals(Integer.valueOf(5), saved.getDaysToComplete());
        assertEquals(Integer.valueOf(3), saved.getCommentCount());
    }
}