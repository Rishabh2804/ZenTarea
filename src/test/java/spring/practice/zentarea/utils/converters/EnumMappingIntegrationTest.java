package spring.practice.zentarea.utils.converters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import spring.practice.zentarea.data.controllers.TaskController;
import spring.practice.zentarea.model.TaskPriority;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class EnumMappingIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPassingLowerCaseEnumConstant_thenConvert() throws Exception {
        mockMvc.perform(get("/tasks/priority?priority=medium"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string(TaskPriority.MEDIUM.name()));
    }

}