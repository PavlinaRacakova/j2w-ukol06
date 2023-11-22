package cz.czechitas.java2webapps.ukol6.controller;

import cz.czechitas.java2webapps.ukol6.messages.Messages;
import cz.czechitas.java2webapps.ukol6.entity.BusinessCard;
import cz.czechitas.java2webapps.ukol6.repository.BusinessCardRepository;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BusinessCardController {

    private final BusinessCardRepository repository;

    public BusinessCardController(BusinessCardRepository repository) {
        this.repository = repository;
    }

    @InitBinder
    public void nullStringBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("list");
        result.addObject("businessCardsList", repository.findAll());
        result.addObject("informationNotProvided", Messages.getInformationNotProvidedMessage());
        return result;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView result = new ModelAndView("detail");
        result.addObject("businessCard", repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        result.addObject("informationNotProvided", Messages.getInformationNotProvidedMessage());
        return result;
    }

    @GetMapping("/new")
    public ModelAndView showNewBusinessCardForm() {
        ModelAndView result = new ModelAndView("businessCardForm");
        result.addObject("businessCard", new BusinessCard());
        result.addObject("title", Messages.getNewBusinessCardTitle());
        return result;
    }

    @PostMapping("/new")
    public Object addNewBusinessCard(@Valid @ModelAttribute BusinessCard businessCard, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("businessCardForm").addObject("title", Messages.getNewBusinessCardTitle());
        }
        repository.save(businessCard);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteBusinessCard(int id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditBusinessCardForm(@PathVariable int id) {
        ModelAndView result = new ModelAndView("businessCardForm");
        result.addObject("businessCard", repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        result.addObject("title", Messages.getEditBusinessCardTitle());
        return result;
    }

    @PostMapping("/edit/{id}")
    public Object editExistingBusinessCard(@Valid @ModelAttribute BusinessCard businessCard, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("businessCardForm").addObject("title", Messages.getEditBusinessCardTitle());
        }
        repository.save(businessCard);
        return "redirect:/detail/{id}";
    }
}
