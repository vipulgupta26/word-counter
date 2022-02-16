package com.ms.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.ms.WordCounterApplication;
import com.ms.service.WordCounterService;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WordCounterApplication.class})
@WebMvcTest(WordController.class)
public class WordControllerTest {

    @Autowired
    private WebApplicationContext context;
                
    private MockMvc mockMvc;

    @MockBean
    private WordCounterService wordCounterService;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    
    @Test
    public void testAddWord() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/add/flower"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertEquals("Added flower", result.getResponse().getContentAsString());
    }

    @Test
    public void testAddWordForNonAlphabet() throws Exception {
        doThrow(Exception.class).when(wordCounterService).addWord(eq("1"));
        mockMvc.perform(MockMvcRequestBuilders.get("/add/1"))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
     }

    @Test
    public void testGetWordOccurrences() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/get/flower"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertEquals("Occurrences for flower: 0", result.getResponse().getContentAsString());
    }

    @Test
    public void testGetWordOccurrencesForNonAlphabet() throws Exception{
        doThrow(Exception.class).when(wordCounterService).getOccurrencesOfWord(eq("1"));
        mockMvc.perform(MockMvcRequestBuilders.get("/get/1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
