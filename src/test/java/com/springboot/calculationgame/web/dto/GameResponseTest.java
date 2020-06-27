package com.springboot.calculationgame.web.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResponseTest {

    @Test
    public void enum필드를_json객체화_했을때() throws JsonProcessingException {
        GameResponse response = new GameResponse(true,0);
        String json = new ObjectMapper().writeValueAsString(response);

        assertThat(json).isEqualTo("{\"solved\":true,\"score\":0}");
    }
}
