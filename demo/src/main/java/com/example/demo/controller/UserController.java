package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    // ArrayList storage
    private List<User> users = new ArrayList<>();

    private int nextId = 1;

    // READ
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", users);
        return "users";
    }

    // CREATE - show form
    @GetMapping("/users/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    // CREATE - save
    @PostMapping("/users/add")
    public String saveUser(@ModelAttribute User user) {

        user.setId(nextId);
        nextId++;

        users.add(user);

        return "redirect:/users";
    }

    // UPDATE - show form
    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable int id, Model model) {

        for (User user : users) {
            if (user.getId() == id) {
                model.addAttribute("user", user);
                return "edit-user";
            }
        }

        return "redirect:/users";
    }

    // UPDATE - save
    @PostMapping("/users/edit/{id}")
    public String updateUser(
            @PathVariable int id,
            @ModelAttribute User updatedUser) {

        for (User user : users) {

            if (user.getId() == id) {

                user.setName(updatedUser.getName());
                user.setPrice(updatedUser.getPrice());

                break;
            }
        }

        return "redirect:/users";
    }

    // DELETE
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {

        users.removeIf(user -> user.getId() == id);

        return "redirect:/users";
    }
}
