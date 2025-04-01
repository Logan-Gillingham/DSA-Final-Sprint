package com.example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TreeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testProcessNumbers() throws Exception {
        mockMvc.perform(post("/process-numbers")
                        .param("numbers", "5,3,7,2,4,6,8")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

    @Test
    void testProcessBalancedNumbers() throws Exception {
        mockMvc.perform(post("/process-balanced-numbers")
                        .param("numbers", "5,3,7,2,4,6,8")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

    @Test
    void testEnterNumbersPage() throws Exception {
        mockMvc.perform(get("/enter-numbers"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Enter Numbers")));
    }

    @Test
    void testPreviousTreesPage() throws Exception {
        mockMvc.perform(get("/previous-trees"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Previous Trees")));
    }

    @Test
    void testProcessNumbersWithInvalidInput() throws Exception {
        mockMvc.perform(post("/process-numbers")
                        .param("numbers", "5,a,7,2,4,6,8")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }
}