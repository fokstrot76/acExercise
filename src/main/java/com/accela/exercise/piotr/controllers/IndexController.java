package com.accela.exercise.piotr.controllers;

import com.accela.exercise.piotr.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService){
        this.personService = personService;
    }

    @RequestMapping({"/",""})
    public String index(Model model){
        model.addAttribute("people", personService.listAll());
        return "index";
    }
}
