package spring.practice.zentarea.model;

import org.junit.jupiter.api.*;
import org.junit.runner.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskPriorityTest {

    private static final Random random;

    static {
        random = new Random();
    }

    private int getRandomInRange(int l, int r) {
        return random.nextInt(l, r - l + 1);
    }

    @Test
    void testResolve() {
        for (TaskPriority priority : TaskPriority.values()) {
            // Object based check
            assertEquals(priority, TaskPriority.resolve(priority));

            // UpperCase check --> "priority": "HIGH"
            String uppercase = priority.toString().toUpperCase();
            assertEquals(priority, TaskPriority.resolve(uppercase));

            // LowerCase check --> "priority": "high"
            String lowercase = priority.toString().toLowerCase();
            assertEquals(priority, TaskPriority.resolve(lowercase));

            for (int i = 0; i < 10; ++i) {
                int val = getRandomInRange(priority.getMinRange(), priority.getMaxRange());
                // Integer value check --> "priority": 51
                assertEquals(priority, TaskPriority.resolve(val));
                // StringInt value check --> "priority": "51"
                assertEquals(priority, TaskPriority.resolve(Integer.toString(val)));
            }
        }
    }
}