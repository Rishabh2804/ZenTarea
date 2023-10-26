package spring.practice.zentarea.model;

import spring.practice.zentarea.utils.extensions.*;

public class BaseEntity {
    @Override
    public String toString() {
        return StringExtension.toString(this);
    }
}
