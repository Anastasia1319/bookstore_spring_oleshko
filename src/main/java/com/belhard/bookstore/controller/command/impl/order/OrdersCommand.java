package com.belhard.bookstore.controller.command.impl.order;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.dto.OrderServiceDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("orders")
@RequiredArgsConstructor
public class OrdersCommand implements Command {
    private final OrderService orderService;
    @Override
    public String execute(HttpServletRequest req) {
        List<OrderServiceDto> orders = orderService.getAll();
        req.setAttribute("orders", orders);
        return "jsp/orders.jsp";
    }
}
