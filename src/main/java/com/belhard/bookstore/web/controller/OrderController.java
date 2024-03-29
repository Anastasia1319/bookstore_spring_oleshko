package com.belhard.bookstore.web.controller;

import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final Sort sort = Sort.by(Sort.Direction.ASC, "id");
    private final Integer pageSize = 5;
    private Pageable pageable;
    private Long totalPages;

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id, Model model) {
        OrderDto order = orderService.getById(id);
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/all/{page}")
    public String getAll(@PathVariable Integer page, Model model) {
        pageable = PageRequest.of(page, pageSize, sort);
        totalPages = orderService.getTotalPages(pageSize);
        List<OrderDto> orders = orderService.getAll(pageable);
        model.addAttribute("orders", orders);
        model.addAttribute("totalPages", totalPages);
        return "orders";
    }

    @GetMapping("/find/{userId}/{page}")
    public String getByUserId(@PathVariable Long userId, @PathVariable Integer page, Model model) {
        pageable = PageRequest.of(page, pageSize, sort);
        totalPages = orderService.getTotalPagesByUser(pageSize, userId);
        List<OrderDto> orders = orderService.getByUserId(userId, pageable);
        model.addAttribute("orders", orders);
        model.addAttribute("totalPages", totalPages);
        return "orders";
    }

    @PostMapping("/delete/{id}")
    public  String delete(@PathVariable Long id) {
        orderService.delete(id);
        return "redirect:/orders/" + id;
    }
}
