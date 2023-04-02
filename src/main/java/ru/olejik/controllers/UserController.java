package ru.olejik.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.olejik.entity.User;
import ru.olejik.exeption.ResourceNotFoundException;
import ru.olejik.repositories.UserRepository;
import ru.olejik.service.user.UserService;

import java.util.logging.Logger;

@RequestMapping("/users")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger = Logger.getAnonymousLogger();

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("user", userService.getUsers());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) throws ResourceNotFoundException {
        model.addAttribute("user", userService.getUser(id));
        return "users/show";
    }
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "users/new";
        userService.createUser(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws ResourceNotFoundException {
        model.addAttribute("user", userService.getUser(id));
        return "users/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult ,@PathVariable("id") int id) throws ResourceNotFoundException {
        if(bindingResult.hasErrors()) return "users/edit";
        userService.updateById(user.getName(),user.getAge(),user.getEmail(),id);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
