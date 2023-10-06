package ru.practicum.shareit.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.validation.ItemRequestCreate;
import ru.practicum.shareit.request.dto.ItemRequestInputDto;
import ru.practicum.shareit.request.client.ItemRequestClient;
import ru.practicum.shareit.validators.PageValidator;

@RestController
@Validated
@RequiredArgsConstructor
public class ItemRequestController {

    private final ItemRequestClient itemRequestClient;
    private final PageValidator pageValidator;

    @PostMapping("/requests")
    public ResponseEntity<Object> addRequest(@RequestHeader(ItemRequestControllerConstants.X_SHARER_USER_ID) Long userId,
                                             @ItemRequestCreate @RequestBody ItemRequestInputDto inputDto) {
        return itemRequestClient.addRequest(userId, inputDto);
    }

    @GetMapping("/requests")
    public ResponseEntity<Object> getRequestsOfUser(@RequestHeader(ItemRequestControllerConstants.X_SHARER_USER_ID) Long userId) {
        return itemRequestClient.getRequestsOfUser(userId);
    }

    @GetMapping("/requests/all")
    public ResponseEntity<Object> getAllRequestsPaged(@RequestHeader(ItemRequestControllerConstants.X_SHARER_USER_ID) Long userId,
                                                      @RequestParam(required = false, defaultValue = "0") Integer from,
                                                      @RequestParam(required = false, defaultValue = "10") Integer size) {
        pageValidator.validatePagination(from, size);
        return itemRequestClient.getAllRequestsPaged(userId, from, size);
    }

    @GetMapping("/requests/{requestId}")
    public ResponseEntity<Object> getRequestById(@RequestHeader(ItemRequestControllerConstants.X_SHARER_USER_ID) Long userId,
                                                 @PathVariable Long requestId) {
        return itemRequestClient.getRequestById(userId, requestId);
    }
}