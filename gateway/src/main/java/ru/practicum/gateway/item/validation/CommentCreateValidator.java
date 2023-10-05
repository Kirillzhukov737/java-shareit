package ru.practicum.gateway.item.validation;

import ru.practicum.gateway.item.dto.CommentInputDto;
import ru.practicum.shareit.exceptions.ValidationException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CommentCreateValidator implements ConstraintValidator<CommentCreate, CommentInputDto> {

    @Override
    public boolean isValid(CommentInputDto comment, ConstraintValidatorContext constraintValidatorContext) {
        if (comment.getText() == null || comment.getText().isBlank()) {
            throw new ValidationException("Comment text cannot be empty");
        }
        return true;
    }
}