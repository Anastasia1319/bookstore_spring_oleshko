package com.belhard.bookstore.controller.command.impl.user;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.exceptions.ApplicationException;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserServiceDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("user")
@RequiredArgsConstructor
public class UserCommand implements Command {
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        long id = processReq(req);
        UserServiceDto userServiceDto = userService.getById(id);
        req.setAttribute("user", userServiceDto);
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
