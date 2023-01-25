package com.belhard.bookstore.controller.command.impl.user;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.data.entity.Role;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("add_user")
@RequiredArgsConstructor
public class AddUserCommand implements Command {
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        UserDto toCreate = processRequest(req);
        userService.save(toCreate);
        UserDto created = userService.getByEmail(toCreate.getEmail());
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
        toCreate.setActive(true);
        return toCreate;
    }
}
