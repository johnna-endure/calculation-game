package com.springboot.calculationgame.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.calculationgame.domain.user.User;
import com.springboot.calculationgame.dto.UserInfo;
import com.springboot.calculationgame.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void user_생성() throws Exception {
        UserInfo userInfo = new UserInfo("username","password");
        String json = new ObjectMapper()
                .writeValueAsString(userInfo);
        when(userService.create(any(User.class))).thenReturn(1l);

        MockHttpServletResponse response = mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        Long id =  Long.parseLong(response.getContentAsString());
        assertThat(id).isEqualTo(1l);

    }
}
