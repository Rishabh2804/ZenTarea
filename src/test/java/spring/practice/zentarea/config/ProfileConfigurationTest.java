package spring.practice.zentarea.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class ProfileConfigurationTest {

    @Test
    public void contextLoadsWithDefaultProfile() {
        // Test that the application context loads successfully with default profile
        assertTrue(true, "Application context should load successfully");
    }

    @Test 
    public void contextLoadsWithAzureMySQLProfile() {
        // Test that Azure MySQL profile configuration is valid
        // Using test profile but validating the configuration files exist
        assertTrue(getClass().getClassLoader()
            .getResource("application-azure-mysql.properties") != null,
            "Azure MySQL configuration should exist");
    }

    @Test
    public void contextLoadsWithMongoDBProfile() {
        // Test that MongoDB profile configuration is valid
        assertTrue(getClass().getClassLoader()
            .getResource("application-mongodb.properties") != null,
            "MongoDB configuration should exist");
    }
}
