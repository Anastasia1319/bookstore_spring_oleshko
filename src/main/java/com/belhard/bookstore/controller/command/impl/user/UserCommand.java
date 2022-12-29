package com.belhard.bookstore.controller.command.impl.user;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.exceptions.ApplicationException;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("user")
public class UserCommand implements Command {
    private final UserService userService;

    public UserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        long id = processReq(req);
        UserDto userDto = userService.getById(id);
        req.setAttribute("user", userDto);
        return "jsp/user.jsp";
    }
    private static long processReq(HttpServletRequest req) {
        try {
            String rawId = req.getParameter("id");
            return Long.parseLong(rawId);
        } catch (Exception e) {
            throw new ApplicationException("Incorrect request data: " + e);
        }
    }
}
