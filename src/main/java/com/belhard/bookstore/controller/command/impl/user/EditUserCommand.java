package com.belhard.bookstore.controller.command.impl.user;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.data.entity.Role;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("edit_user")
public class EditUserCommand implements Command {
    private final UserService userService;

    public EditUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        UserDto toEdit = processRequest(req);
        UserDto edited = userService.update(toEdit);
        req.setAttribute("user", edited);
        return "jsp/user.jsp";
    }

    private UserDto processRequest(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        UserDto toEdit = userService.getById(id);
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Role role = Role.valueOf(req.getParameter("role"));
        toEdit.setFirstName(firstName);
        toEdit.setLastName(lastName);
        toEdit.setEmail(email);
        toEdit.setPassword(password);
        toEdit.setRole(role);
        return toEdit;
    }
}
