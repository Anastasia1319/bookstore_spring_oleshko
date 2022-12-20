package com.belhard.bookstore.controller.command.impl.user;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class UsersCommand implements Command {
    private final UserService userService;

    public UsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<UserDto> users = userService.getAll();
        req.setAttribute("users", users);
        return "jsp/users.jsp";
    }
}
