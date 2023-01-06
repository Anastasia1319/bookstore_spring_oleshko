package com.belhard.bookstore.controller.command.impl.user;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("find_user")
public class FindUserCommand implements Command {
    private final UserService userService;

    public FindUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String email = req.getParameter("email");
        UserDto userDto = userService.getByEmail(email);
        req.setAttribute("user", userDto);
        return "jsp/user.jsp";
    }
}
