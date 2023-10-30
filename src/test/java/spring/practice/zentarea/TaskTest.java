package spring.practice.zentarea;

import spring.practice.zentarea.model.*;

import java.util.*;

public final class TaskTest {

    public static final List<Task> TASKS = new ArrayList<>();
    public static final String[] TEST_TASKS_JSON;

    // @formatter:off
    static {
        // <----------- Create Task ------------>
        TASKS.add(
            new Task(
                "Task 1",
                "This is task #1",
                new Date(),
                "In Progress",
                "HIGH"
            ));

        TASKS.add(
            new Task(
                "Task 2",
                "This is the task #2",
                new Date(),
                "In Progress",
                TaskPriority.MEDIUM
            ));

        TASKS.add(
            new Task(
                "Task 3",
                "This is the task #3",
                new Date(),
                "In Progress",
                51
            ));


        // <--------- JSON Tasks --------->
        TEST_TASKS_JSON = new String[]
        {
            "{ " +
                "\"title\": \"Task 3\", " +
                " \"description\": \"This is the task #3\", " +
                " \"dueDate\": \"2021-10-10T00:00:00.000+00:00\", " +
                " \"status\": \"In Progress\", " +
                "\"priority\": \"high\" " +
           "}",
            "{ " +
                "\"title\": \"Task 3\", " +
                " \"description\": \"This is the task #3\", " +
                " \"dueDate\": \"2021-10-10T00:00:00.000+00:00\", " +
                " \"status\": \"In Progress\", " +
                "\"priority\": \"Low\" " +
            "}",
            "{ " +
                "\"title\": \"Task 3\", " +
                " \"description\": \"This is the task #3\", " +
                " \"dueDate\": \"2021-10-10T00:00:00.000+00:00\", " +
                " \"status\": \"In Progress\", " +
                "\"priority\": 51 " +
            "}",
        };
    }
    // @formatter:on
}
