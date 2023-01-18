package com.belhard.bookstore.controller.command.impl.user;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.data.entity.Role;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserServiceDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("edit_user")
@RequiredArgsConstructor
public class EditUserCommand implements Command {
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        UserServiceDto toEdit = processRequest(req);
        userService.save(toEdit);
        UserServiceDto edited = userService.getById(toEdit.getId());
        req.setAttribute("user", edited);
        return "jsp/user.jsp";
    }

    private UserServiceDto processRequest(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        UserServiceDto toEdit = userService.getById(id);
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Role role = Role.valueOf(req.getParameter("role"));
        boolean isActive = true;
        toEdit.setFirstName(firstName);
        toEdit.setLastName(lastName);
        toEdit.setEmail(email);
        toEdit.setPassword(password);
        toEdit.setRole(role);
        toEdit.setActive(isActive);
        return toEdit;
    }
}
