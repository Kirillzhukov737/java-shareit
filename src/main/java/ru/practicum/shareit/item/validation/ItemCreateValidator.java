package ru.practicum.shareit.item.validation;

import ru.practicum.shareit.exceptions.ValidationException;
import ru.practicum.shareit.item.dto.ItemInputDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ItemCreateValidator implements ConstraintValidator<ItemCreate, ItemInputDto> {

    @Override
    public boolean isValid(ItemInputDto item, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(item.getName()) || item.getName().isBlank()) {
            throw new ValidationException("name cannot be blank");
        }
        if (Objects.isNull(item.getDescription()) || item.getDescription().isBlank()) {
            throw new ValidationException("description cannot be blank");
        }
        if (Objects.isNull(item.getAvailable())) {
            throw new ValidationException("available cannot be null");
        }
        return true;
    }
}