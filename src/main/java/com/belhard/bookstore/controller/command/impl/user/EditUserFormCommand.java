package com.belhard.bookstore.controller.command.impl.user;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.exceptions.ApplicationException;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserServiceDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("edit_user_form")
@RequiredArgsConstructor
public class EditUserFormCommand implements Command {
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        Long id = processReq(req);
        UserServiceDto userServiceDto = userService.getById(id);
        req.setAttribute("user", userServiceDto);
        return "jsp/edit_user.jsp";
    }

    private static long processReq(HttpServletRequest req) {
        try {
            String rawId = req.getParameter("id");
            return Long.parseLong(rawId);
        } catch (RuntimeException e) {
            throw new ApplicationException("Incorrect request data: " + e);
        }
    }
}
