package ru.practicum.shareit.item.model;

import lombok.*;
import ru.practicum.shareit.user.model.User;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Comment {

    private Long id;
    private String text;
    private User author;
    private Long created;
    private Long itemId;
}