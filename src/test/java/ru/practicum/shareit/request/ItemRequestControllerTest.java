package ru.practicum.shareit.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.shareit.request.controller.ItemRequestController;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestInputDto;
import ru.practicum.shareit.request.service.ItemRequestService;
import ru.practicum.shareit.request.controller.ItemRequestControllerConstants;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ItemRequestController.class)
public class ItemRequestControllerTest {

    @Autowired
    ObjectMapper mapper;
    @MockBean
    private ItemRequestService itemRequestService;
    @Autowired
    private MockMvc mvc;
    private ItemRequestDto itemRequestDto;

    @BeforeEach
    void setUpItemRequestDto() {
        itemRequestDto = ItemRequestDto.builder()
                .id(1L)
                .description("description")
                .build();
    }

    @Test
    void testAddRequest() throws Exception {
        ItemRequestInputDto inputDto = ItemRequestInputDto.builder()
                .build();

        when(itemRequestService.addRequest(any(), any()))
                .thenReturn(itemRequestDto);

        mvc.perform(post("/requests")
                        .header(ItemRequestControllerConstants.X_SHARER_USER_ID, 1L)
                        .content(mapper.writeValueAsString(null))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/requests")
                        .header(ItemRequestControllerConstants.X_SHARER_USER_ID, 1L)
                        .content(mapper.writeValueAsString(inputDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        inputDto.setDescription(" ");

        mvc.perform(post("/requests")
                        .header(ItemRequestControllerConstants.X_SHARER_USER_ID, 1L)
                        .content(mapper.writeValueAsString(inputDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        inputDto.setDescription("description");

        mvc.perform(post("/requests")
                        .header(ItemRequestControllerConstants.X_SHARER_USER_ID, 1L)
                        .content(mapper.writeValueAsString(inputDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(itemRequestDto.getId()), Long.class));
    }

    @Test
    void testGetRequestsOfUser() throws Exception {
        when(itemRequestService.getItemRequestsOfUser(any()))
                .thenReturn(List.of(itemRequestDto, itemRequestDto));
        mvc.perform(get("/requests").header(ItemRequestControllerConstants.X_SHARER_USER_ID, 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].description", is(itemRequestDto.getDescription()), String.class))
                .andExpect(jsonPath("$.[1].description", is(itemRequestDto.getDescription()), String.class));
    }

    @Test
    void testGetRequestById() throws Exception {
        when(itemRequestService.getItemRequestById(any(), any()))
                .thenReturn(itemRequestDto);
        mvc.perform(get("/requests/1").header(ItemRequestControllerConstants.X_SHARER_USER_ID, 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(itemRequestDto.getDescription()), String.class));
    }

    @Test
    void testGetAllRequests() throws Exception {
        when(itemRequestService.getAllRequestsPaged(any(), any(), any()))
                .thenReturn(List.of(itemRequestDto, itemRequestDto));
        mvc.perform(get("/requests/all").header(ItemRequestControllerConstants.X_SHARER_USER_ID, 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].description", is(itemRequestDto.getDescription()), String.class))
                .andExpect(jsonPath("$.[1].description", is(itemRequestDto.getDescription()), String.class));
    }
}