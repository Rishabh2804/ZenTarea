package spring.practice.zentarea.data.controllers;

import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.*;
import spring.practice.zentarea.*;
import spring.practice.zentarea.model.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * TODO: 30-10-2023
 * <ul>
 *  <li>Learn about {@link SpringBootTest}</li>
 *  <li>Learn about {@link SpringRunner}</li>
 *  <li>Diff between {@link WebMvcTest} and {@link SpringBootTest},
 *   and a way to use former in place of latter</li>
 *  </ul>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testCreateTask() throws Exception {
        for (Task task : TaskTest.TASKS) {

            String taskJson = objectMapper.writeValueAsString(task);
            mockMvc.perform(post("/tasks")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(taskJson))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.title").value(task.getTitle()))
                    .andExpect(jsonPath("$.description").value(task.getDescription()))
                    .andExpect(jsonPath("$.priority").value(task.getPriority().toString()));
        }
    }
}
