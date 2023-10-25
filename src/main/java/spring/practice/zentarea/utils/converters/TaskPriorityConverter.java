package spring.practice.zentarea.utils.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.practice.zentarea.model.TaskPriority;

@Component
public class TaskPriorityConverter implements Converter<String, TaskPriority> {

    @Override
    public TaskPriority convert(String source) {
        return TaskPriority.parse(source);
    }
}

