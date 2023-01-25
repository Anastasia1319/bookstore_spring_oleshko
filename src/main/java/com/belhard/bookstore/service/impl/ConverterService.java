package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.OrderItemRepository;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.service.dto.BookServiceDto;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.service.dto.OrderDto;
import com.belhard.bookstore.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class ConverterService {
    private final OrderItemRepository orderItemRepository;
    public Book toBookEntity (BookServiceDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setPublishinYear(dto.getPublishinYear());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());
        log.info("BookDto transformed to Book");
        return book;
    }

    public BookServiceDto toBookDto(Book book) {
        BookServiceDto bookServiceDto = new BookServiceDto();
        bookServiceDto.setId(book.getId());
        bookServiceDto.setAuthor(book.getAuthor());
        bookServiceDto.setTitle(book.getTitle());
        bookServiceDto.setPublishinYear(book.getPublishinYear());
        bookServiceDto.setIsbn(book.getIsbn());
        bookServiceDto.setPrice(book.getPrice());
        log.info("Book transformed to BookDto");
        return bookServiceDto;
    }

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        log.info("User transformed to UserDto");
        return userDto;
    }

    public User toUserEntity (UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setActive(dto.isActive());
        log.info("UserDto transformed to User");
        return user;
    }

    public OrderDto toOrderDto (Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUser(order.getUser());
        orderDto.setStatus(OrderDto.Status.valueOf(order.getStatus().toString()));
        orderDto.setTotalCost(orderItemRepository.findTotalCost(order.getId()));
        orderDto.setItems(order.getItems());
        log.info("Order transformed to OrderServiceDto");
        return orderDto;
    }
}
