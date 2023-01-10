package com.belhard.bookstore.controller.command.impl.user;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserServiceDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("find_user")
@RequiredArgsConstructor
public class FindUserCommand implements Command {
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        String email = req.getParameter("email");
        UserServiceDto userServiceDto = userService.getByEmail(email);
        req.setAttribute("user", userServiceDto);
        return "jsp/user.jsp";
    }
}
