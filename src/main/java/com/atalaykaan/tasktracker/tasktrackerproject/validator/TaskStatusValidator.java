package com.atalaykaan.tasktracker.tasktrackerproject.validator;

import com.atalaykaan.tasktracker.tasktrackerproject.annotation.ValidTaskStatus;
import com.atalaykaan.tasktracker.tasktrackerproject.model.TaskStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskStatusValidator implements ConstraintValidator<ValidTaskStatus, TaskStatus> {

    private List<String> acceptedValues;

    @Override
    public void initialize(ValidTaskStatus annotation) {

        acceptedValues = Stream.of(annotation.enumClass()
                .getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(TaskStatus value, ConstraintValidatorContext context) {

        if(value == null) {

            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}