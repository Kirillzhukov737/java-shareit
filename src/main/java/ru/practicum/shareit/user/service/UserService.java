package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {
    /**
     * Создание нового пользователя.
     */
    UserDto createUser(User user);

    /**
     * Получение пользователя по его Id.
     */
    UserDto getUserById(Long userId);

    /**
     * Обновление информации о пользователе.
     */
    UserDto updateUser(User user, Long userId);

    /**
     * Удаление пользователя по его Id.
     */
    UserDto deleteUser(Long userId);

    /**
     * Получение списка всех пользователей.
     */
    List<UserDto> getAllUsers();
}