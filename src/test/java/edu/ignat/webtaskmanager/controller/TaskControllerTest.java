package edu.ignat.webtaskmanager.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Successful TaskController test")
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(view().name("test"))
                .andExpect(content().string(containsString("Test controller is ok")));
    }

}