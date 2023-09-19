package ru.practicum.shareit.item.validation;

import ru.practicum.shareit.item.model.Item;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

public class ItemCreateValidator implements ConstraintValidator<ItemCreate, Item> {

    @Override
    public boolean isValid(Item item, ConstraintValidatorContext constraintValidatorContext) {
        if (item.getName() == null || item.getName().isBlank()) {
            throw new ValidationException("Name cannot be blank");
        }
        if (item.getDescription() == null || item.getDescription().isBlank()) {
            throw new ValidationException("Description cannot be blank");
        }
        if (item.getAvailable() == null) {
            throw new ValidationException("Available cannot be null");
        }
        return true;
    }
}