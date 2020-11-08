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


@RequestMapping("/person")
@Controller
public class PersonController {

    private PersonService personService;
    @Autowired
    public void setPersonService(PersonService personService){
        this.personService = personService;
    }

    private AddressService addressService;
    @Autowired
    public void setAddressService(AddressService addressService){
        this.addressService = addressService;
    }

    @RequestMapping({"/list", "/"})
    public String listOfPeople(Model model){
        model.addAttribute("people", personService.listAll());
        return "person/list";
    }

    @RequestMapping("/show/{id}")
    public String showPerson(@PathVariable Integer id, Model model){
        Person person = personService.getById(id);
        model.addAttribute("person", person);
        if(person.getAddressId() != null){
            Address address = addressService.getById(person.getAddressId());
            model.addAttribute("address", address);
            return "person/showAll";
        }
        return "person/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("person", personService.getById(id));
        return "person/form";
    }

    @RequestMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "person/form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String update(Person newPerson){
        Person person = personService.saveOrUpdate(newPerson);
        if(person.getAddressId() == null){
            return "redirect:address/new/" + person.getId();
        }
        return "redirect:person/show/" + person.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        personService.delete(id);
        return "redirect:/person/list";
    }
}
