package me.june.demottdpractice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.june.demottdpractice.entity.Board;
import me.june.demottdpractice.repository.BoardRepository;
import me.june.demottdpractice.service.BoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Autowired
    ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(BoardControllerTest.class);

    @Test
    public void list() throws Exception {
       /**
         * Mockito
         *
         * MockBean사용시 주의점
         * mocking target method 에 arguments가 존재한다면 , any() any..() 메서드를 사용하여 넣어주어야 mocking이 적용된다.
         */
       // given(boardService.findAll(any(Board.class))).willReturn(Arrays.asList(new Board()));
        mockMvc.perform(get("/boards"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string("[]"));
    }

    @Test
    public void create() throws  Exception {

        Board board = Board.builder()
                            .writer("june")
                            .content("i'm june")
                            .title("introduce")
                            .createdAt(LocalDate.of(2019,2,12))
                            .build();

        //given(boardService.createBoard(any(Board.class))).willReturn(board);


        //String json = objectMapper.writeValueAsString(board);

     //   logger.debug("json = {}",json);

        mockMvc.perform(post("/boards")
                        .param("writer","june")
                        .param("content","i'm june")
                        .param("title","introduce"))
                        .andDo(print())
                        .andExpect(status().isOk())
          //              .andExpect(content().string(json))
                        .andExpect(jsonPath("$.writer").value("june"))
                        .andExpect(jsonPath("$.content").value("i'm june"))
                        .andExpect(jsonPath("$.title").value("introduce"));
    }

    @Test
    public void getBoard() throws Exception {
        mockMvc.perform(get("/boards/2"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.writer").value("june"))
                        .andExpect(jsonPath("$.content").value("i'm june"))
                        .andExpect(jsonPath("$.title").value("introduce"));
    }

    @Test
    public void updateBoard() throws Exception {
        mockMvc.perform(put("/boards/2")
                        .param("writer","june")
                        .param("content","i'm june")
                        .param("title","introduce"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.writer").value("june"))
                        .andExpect(jsonPath("$.content").value("i'm june"))
                        .andExpect(jsonPath("$.title").value("introduce"));
    }
}