package ru.practicum.shareit.validators;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.ValidationException;

@Service
public class PageValidatorImp implements PageValidator {
    @Override
    public void validatePagination(Integer from, Integer size) {
        if (size <= 0 || from < 0) {
            throw new ValidationException("invalid page parameters");
        }
    }
}