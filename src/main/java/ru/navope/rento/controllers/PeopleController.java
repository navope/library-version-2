package ru.navope.rento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.navope.rento.models.Person;
import ru.navope.rento.services.BookService;
import ru.navope.rento.services.PersonService;
import ru.navope.rento.util.PersonValidator;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person,bindingResult);

        if(bindingResult.hasErrors()){
            return "people/new";
        }
        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person",personService.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (!Objects.equals(person.getFullName(), personService.getPerson(id).getFullName())){
            personValidator.validate(person,bindingResult);
        }

        if(bindingResult.hasErrors()){
            return "people/edit";
        }
        personService.update(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }

    @GetMapping()
    public String showPeople(Model model){
        model.addAttribute("people", personService.getPeople());
        return "people/showPeople";
    }

    @GetMapping("/{id}")
    public String showPerson(Model model, @PathVariable("id") int id){
        Person person = personService.getPerson(id);
        model.addAttribute("person", person);
//        model.addAttribute("books", person.getBooks());
        model.addAttribute("books", personService.getPersonBooks(id));
        return "people/show";
    }
}
