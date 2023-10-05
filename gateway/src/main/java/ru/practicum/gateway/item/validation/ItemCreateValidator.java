package ru.practicum.gateway.item.validation;

import ru.practicum.gateway.item.dto.ItemInputDto;
import ru.practicum.shareit.exceptions.ValidationException;

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