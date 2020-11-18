package com.accela.exercise.piotr.controllers;

import com.accela.exercise.piotr.domains.Person;
import com.accela.exercise.piotr.services.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IndexControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private IndexController indexController;
    @Mock
    private PersonService personService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    public void testIndexSlash() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testIndex() throws Exception{
        List<Person>personList = new ArrayList<>();
        personList.add(new Person());
        when(personService.listAll()).thenReturn((List)personList);
        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("people", hasSize(1)));
    }
}