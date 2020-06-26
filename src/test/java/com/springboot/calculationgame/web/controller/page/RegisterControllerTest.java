package com.springboot.calculationgame.web.controller.page;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void mockMvc_isNotNull(){
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void 회원가입페이지_로딩() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk());
    }

}
