package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.CommentInputDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {

    /**
     * Получает список DTO объектов Item.
     */
    List<ItemDto> getAllItemsOfUser(Long userId);

    /**
     * Получает DTO объект Item по Id.
     */
    ItemDto getItemById(Long itemId, Long userId);

    /**
     * Добавляет новый объект Item для указанного пользователя и возвращает его DTO представление.
     */
    ItemDto addItem(Item item, Long userId);

    /**
     * Обновляет существующий объект Item для указанного пользователя и возвращает его обновленное DTO.
     */
    ItemDto updateItem(Item item, Long userId);

    /**
     * Поиск объектов Item, содержащих указанный текст, принадлежащих указанному пользователю.
     */
    List<ItemDto> searchItem(String text);

    /**
     * Удаляет объект Item по его Id и возвращает его DTO представление перед удалением.
     */
    ItemDto deleteItem(Long itemId);

    /**
     * Добавляет комментарий.
     */
    CommentDto addComment(CommentInputDto commentInputDto);
}