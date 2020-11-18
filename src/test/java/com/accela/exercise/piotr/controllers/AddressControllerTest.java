package com.accela.exercise.piotr.controllers;

import com.accela.exercise.piotr.domains.Address;
import com.accela.exercise.piotr.domains.Person;
import com.accela.exercise.piotr.services.AddressService;
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

public class AddressControllerTest {

    private MockMvc mockMvc;
    @Mock
    private AddressService addressService;
    @Mock
    private PersonService personService;
    @InjectMocks
    private AddressController addressController;
    @InjectMocks
    private PersonController personController;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
    }

    @Test
    public void listOfAddresses() throws Exception{
        List<Address>addressList = new ArrayList<>();
        addressList.add(new Address());
        addressList.add(new Address());

        when(addressService.listAll()).thenReturn((List)addressList);

        mockMvc.perform(get("/address/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("address/list"))
                .andExpect(model().attribute("addresses", hasSize(2)));
    }

    @Test
    public void showAddress() throws Exception{
        when(addressService.getById(1)).thenReturn(new Address());

        mockMvc.perform(get("/address/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("address/show"))
                .andExpect(model().attribute("address", instanceOf(Address.class)));
    }

    @Test
    public void edit() throws Exception{
        when(addressService.getById(1)).thenReturn(new Address());

        mockMvc.perform(get("/address/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("address/form"))
                .andExpect(model().attribute("address", instanceOf(Address.class)));
    }

    @Test
    public void newAddress() throws Exception{
        mockMvc.perform(get("/address/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("address/form"))
                .andExpect(model().attribute("address", instanceOf(Address.class)));
    }

    @Test
    public void testNewAddress() throws Exception{
        mockMvc.perform(get("/address/new/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("address/form"))
                .andExpect(model().attribute("address", instanceOf(Address.class)));
    }

    @Test
    public void update() throws Exception{
        Address address = new Address();
        address.setId(1);
        address.setStreet("Street");
        address.setCity("Dublin");
        address.setState("State");
        address.setPostalCode("code");
        when(personService.getById(1)).thenReturn(new Person());
        when(addressService.saveOrUpdate(Matchers.any())).thenReturn(address);

        mockMvc.perform(post("/address")
                .param("id", "1")
                .param("street", "Street")
                .param("city", "Dublin")
                .param("state", "State")
                .param("postalCode", "code")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:address/show/1"))
                .andExpect(model().attribute("address",hasProperty("city", is("Dublin"))));
    }

    @Test
    public void delete() throws Exception{
        when(personService.getById(1)).thenReturn(new Person());

        mockMvc.perform(get("/address/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/address/list"));
    }
}