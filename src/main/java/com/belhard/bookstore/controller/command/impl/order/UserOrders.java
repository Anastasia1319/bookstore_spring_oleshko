package com.belhard.bookstore.controller.command.impl.order;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.exceptions.ApplicationException;
import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.dto.OrderServiceDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("user_orders")
@RequiredArgsConstructor
public class UserOrders implements Command {
    private final OrderService orderService;

    @Override
    public String execute(HttpServletRequest req) {
        Long id = processReq(req);
        int page = Integer.valueOf(req.getParameter("page"));
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        List<OrderServiceDto> orders = orderService.getByUserId(id, pageable);
        Long totalPages = orderService.totalPages(pageSize);
        String command = "orders";
        req.setAttribute("orders", orders);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("page", page);
        req.setAttribute("command", command);
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
