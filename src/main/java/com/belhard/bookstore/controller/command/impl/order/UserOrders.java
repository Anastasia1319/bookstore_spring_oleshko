package com.belhard.bookstore.controller.command.impl.order;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.exceptions.ApplicationException;
import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.OrderServiceDto;
import com.belhard.bookstore.service.dto.UserServiceDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("user_orders")
@RequiredArgsConstructor
public class UserOrders implements Command {
    private final OrderService orderService;
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        Long id = processReq(req);
        UserServiceDto userDto = userService.getById(id);
        List<OrderServiceDto> orders = orderService.getByUserId(userDto);
        req.setAttribute("orders", orders);
        return "jsp/orders.jsp";
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
