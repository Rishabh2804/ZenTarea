package spring.practice.zentarea.data.service.analytics;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Basic test to ensure Analytics configuration loads properly
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TaskAnalyticsServiceTest {

    @Test
    public void contextLoads() {
        // This test ensures that the Spring context loads successfully
        // with the new Redshift configuration
    }
}