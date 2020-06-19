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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void mockMvc_isNotNull() {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void userService_isNotNull() {
        assertThat(userService).isNotNull();
    }

    @Test
    public void loginCheck_로그인_정보가_일치하는경우() throws Exception {
        UserInfo userInfo = new UserInfo("username", "1234");
        when(userService.checkUser(any(User.class))).thenReturn(1l);

        MockHttpServletResponse response = mockMvc.perform(
                post("/user/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(new ObjectMapper().writeValueAsString(userInfo)))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).isEqualTo("1");
    }

    @Test
    public void loginCheck_로그인_정보가_일치하지않는경우() throws Exception {
        UserInfo userInfo = new UserInfo("username", "1234");
        when(userService.checkUser(any(User.class))).thenReturn(-1l);

        MockHttpServletResponse response = mockMvc.perform(
                post("/user/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(new ObjectMapper().writeValueAsString(userInfo)))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).isEqualTo("-1");
    }
}
