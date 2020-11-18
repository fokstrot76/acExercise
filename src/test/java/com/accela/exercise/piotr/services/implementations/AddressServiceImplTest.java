package com.accela.exercise.piotr.services.implementations;

import com.accela.exercise.piotr.controllers.AddressController;
import com.accela.exercise.piotr.domains.Address;
import com.accela.exercise.piotr.services.AddressService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


public class AddressServiceImplTest {

    private MockMvc mockMvc;
    @Mock
    private AddressService addressService;
    @InjectMocks
    private AddressController addressController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
    }


    @Test
    public void listAll() throws Exception{
        List<Address>addressList = new ArrayList<>();
        addressList.add(new Address());
        addressList.add(new Address());

        when(addressService.listAll()).thenReturn((List)addressList);

        List<Address>testAddressList = (List<Address>) addressService.listAll();
        assert testAddressList.size() == 2;
    }
}