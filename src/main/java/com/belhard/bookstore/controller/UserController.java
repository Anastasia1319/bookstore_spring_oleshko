package com.belhard.bookstore.controller;


import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        UserDto user = userService.getById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/all/{page}")
    public String getAll(@PathVariable Integer page, Model model) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Long totalPages = userService.totalPagesActive(pageSize);
        List<UserDto> users = userService.getAll(pageable);
        model.addAttribute("users", users);
        model.addAttribute("totalPages", totalPages);
        return "users";
    }

    @GetMapping("/create")
    public String createUserForm() {
        return "add_user";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute UserDto user) {
        UserDto created = userService.save(user);
        return "redirect:/users/" + created.getId();
    }
}
