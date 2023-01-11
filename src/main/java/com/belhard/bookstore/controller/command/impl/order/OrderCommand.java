package com.belhard.bookstore.controller.command.impl.order;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.exceptions.ApplicationException;
import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.dto.OrderServiceDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("order")
@RequiredArgsConstructor
public class OrderCommand implements Command {
    private final OrderService orderService;
    
    @Override
    public String execute(HttpServletRequest req) {
        Long id = processReq(req);
        OrderServiceDto orderServiceDto = orderService.getById(id);
        req.setAttribute("order", orderServiceDto);
        return "jsp/order.jsp";
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
