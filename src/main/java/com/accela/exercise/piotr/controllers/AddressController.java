package com.accela.exercise.piotr.controllers;

import com.accela.exercise.piotr.domains.Address;
import com.accela.exercise.piotr.domains.Person;
import com.accela.exercise.piotr.services.AddressService;
import com.accela.exercise.piotr.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/address")
@Controller
public class AddressController {

    private AddressService addressService;
    @Autowired
    public void setAddressService(AddressService addressService){
        this.addressService = addressService;
    }

    private PersonService personService;
    @Autowired
    public void setPersonService(PersonService personService){
        this.personService = personService;
    }

    private Integer personId;

    @RequestMapping({"/list", "/"})
    public String listOfAddresses(Model model){
        model.addAttribute("addresses", addressService.listAll());
        return "address/list";
    }

    @RequestMapping("/show/{id}")
    public String showAddress(@PathVariable Integer id, Model model){
        model.addAttribute("address", addressService.getById(id));
        return "address/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("address", addressService.getById(id));
        return "address/form";
    }

    @RequestMapping("/new")
    public String newAddress(Model model){
        model.addAttribute("address", new Address());
        return "address/form";
    }

    @RequestMapping("/new/{personId}")
    public String newAddress(@PathVariable Integer personId, Model model){
        this.personId = personId;
        model.addAttribute("address", new Address());
        return "address/form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String update(Address newAddress){
        Address address = addressService.saveOrUpdate(newAddress);
        Person person = personService.getById(personId);
        if(person != null){
            person.setAddressId(address.getId());
            personService.saveOrUpdate(person);
        }
        return "redirect:address/show/" + address.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        addressService.delete(id);
        Person person = personService.getById(personId);
        if(person != null){
            person.setAddressId(null);
            personService.saveOrUpdate(person);
        }
        return "redirect:/address/list";
    }
}
