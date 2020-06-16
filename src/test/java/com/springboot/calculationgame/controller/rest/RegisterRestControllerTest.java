package com.springboot.calculationgame.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.calculationgame.domain.user.UserRepository;
import com.springboot.calculationgame.dto.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@DataJpaTest
public class RegisterRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Test
    public void user_생성() throws Exception {
        String json = new ObjectMapper()
                .writeValueAsString(new UserInfo("username","password"));
        MockHttpServletResponse response = mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        Long responseId =  Long.parseLong(response.getContentAsString());
        assertThat(responseId).isGreaterThan(0l);
    }
}
