package com.example.boardmanager.controller;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.boardmanager.dto.request.BoardRequestDto;
import com.example.boardmanager.model.Board;
import com.example.boardmanager.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@WithMockUser
public class BoardControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BoardService boardService;

    @Test
    public void get_Ok() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/boards"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void add_Ok() throws Exception {
        BoardRequestDto board = new BoardRequestDto();
        board.setName("name");
        mockMvc.perform(MockMvcRequestBuilders.post("/boards")
                .content(asJsonString(board))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void delete_Ok() throws Exception {
        BoardRequestDto board = new BoardRequestDto();
        board.setName("name");
        mockMvc.perform(MockMvcRequestBuilders.post("/boards")
                .content(asJsonString(board))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        mockMvc.perform(MockMvcRequestBuilders.delete("/boards/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void image_Ok() throws Exception {
        Board board = new Board();
        board.setName("name");
        boardService.save(board);
        Long id = board.getId();
        mockMvc.perform(MockMvcRequestBuilders
                .put("/boards/image/" + id
                        + "?file-path=src/test/resources/image_2021-03-12_18-01-38.png"))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/boards/image/download/" + id))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
