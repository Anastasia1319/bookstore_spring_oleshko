package com.belhard.bookstore.controller.command.impl.user;

import com.belhard.bookstore.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("add_user_form")
public class AddUserFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/add_user.jsp";
    }
}
