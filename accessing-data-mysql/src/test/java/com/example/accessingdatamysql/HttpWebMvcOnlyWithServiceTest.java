package com.example.accessingdatamysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
Only the web layer is initialized, not the entire Spring Application Context.
* */
//@WebMvcTest
@WebMvcTest(UserController.class)
public class HttpWebMvcOnlyWithServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testHttp() throws Exception {
        when(userService.getUsers()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/user/all")).andDo(print()).andExpect(status().isOk());
    }
}
