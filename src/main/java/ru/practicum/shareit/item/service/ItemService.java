package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.CommentInputDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemInputDto;

import java.util.List;

public interface ItemService {

    /**
     * Получает список DTO объектов Item.
     */
    List<ItemDto> getAllItemsOfUser(Long userId, Integer from, Integer size);

    /**
     * Получает DTO объект Item по Id.
     */
    ItemDto getItemById(Long itemId, Long userId);

    /**
     * Добавляет новый объект Item для указанного пользователя и возвращает его DTO представление.
     */
    ItemDto addItem(ItemInputDto itemInputDto, Long userId);

    /**
     * Обновляет существующий объект Item для указанного пользователя и возвращает его обновленное DTO.
     */
    ItemDto updateItem(ItemInputDto itemInputDto, Long userId);

    /**
     * Поиск объектов Item, содержащих указанный текст, принадлежащих указанному пользователю.
     */
    List<ItemDto> searchItem(String text, Integer from, Integer size);

    /**
     * Удаляет объект Item по его Id и возвращает его DTO представление перед удалением.
     */
    ItemDto deleteItem(Long itemId);

    /**
     * Добавляет комментарий.
     */
    CommentDto addComment(CommentInputDto commentInputDto);
}