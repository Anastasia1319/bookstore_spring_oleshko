package com.belhard.bookstore.controller.command.impl.user;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.data.entity.Role;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("add_user")
public class AddUserCommand implements Command {
    private final UserService userService;

    public AddUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        UserDto toCreate = processRequest(req);
        UserDto created = userService.create(toCreate);
        req.setAttribute("user", created);
        return "jsp/user.jsp";
    }

    private static UserDto processRequest(HttpServletRequest req) {
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Role role = Role.valueOf(req.getParameter("role"));
        UserDto toCreate = new UserDto();
        toCreate.setFirstName(firstName);
        toCreate.setLastName(lastName);
        toCreate.setEmail(email);
        toCreate.setPassword(password);
        toCreate.setRole(role);
        return toCreate;
    }
}
