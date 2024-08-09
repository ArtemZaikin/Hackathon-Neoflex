package ru.wakeupneo.recruiting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RecruitingApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
	void contextLoads() {
	}

    @Test
    public void getStatement() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
