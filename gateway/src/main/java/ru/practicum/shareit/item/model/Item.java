package ru.practicum.shareit.item.model;

import lombok.*;
import ru.practicum.shareit.user.model.User;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Long id;
    private String name;
    private String description;
    @EqualsAndHashCode.Exclude
    private Boolean available;
    private User user;
    private Set<Comment> comments = new HashSet<>();
    private Long requestId;
}