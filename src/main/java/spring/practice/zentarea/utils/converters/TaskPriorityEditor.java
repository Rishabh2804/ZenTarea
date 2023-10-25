package spring.practice.zentarea.utils.converters;

import io.micrometer.common.util.StringUtils;
import spring.practice.zentarea.model.TaskPriority;

import java.beans.PropertyEditorSupport;

public class TaskPriorityEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        if (StringUtils.isBlank(text)) {
            setValue(null);
        } else {
            setValue(TaskPriority.parse(text));
        }
    }
}