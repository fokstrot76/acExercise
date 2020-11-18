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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PersonControllerTest {
    @Mock
    private PersonService personService;
    @InjectMocks
    private PersonController personController;

    private MockMvc mockMvc;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }


    @Test
    public void listOfPeople() throws Exception {
        List<Person>people = new ArrayList<>();
        people.add(new Person());
        people.add(new Person());

        when(personService.listAll()).thenReturn((List)people);

        mockMvc.perform(get("/person/list"))
        .andExpect(status().isOk())
        .andExpect(view().name("person/list"))
        .andExpect(model().attribute("people", hasSize(2)));
    }

}