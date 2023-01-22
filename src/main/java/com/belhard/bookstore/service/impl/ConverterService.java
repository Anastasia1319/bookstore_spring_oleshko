package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.OrderItemRepository;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.service.dto.BookServiceDto;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.service.dto.OrderServiceDto;
import com.belhard.bookstore.service.dto.UserServiceDto;
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
        book.setPublishingYear(dto.getPublishingYear());
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
        bookServiceDto.setPublishingYear(book.getPublishingYear());
        bookServiceDto.setIsbn(book.getIsbn());
        bookServiceDto.setPrice(book.getPrice());
        log.info("Book transformed to BookDto");
        return bookServiceDto;
    }

    public UserServiceDto toUserDto(User user) {
        UserServiceDto userServiceDto = new UserServiceDto();
        userServiceDto.setId(user.getId());
        userServiceDto.setFirstName(user.getFirstName());
        userServiceDto.setLastName(user.getLastName());
        userServiceDto.setEmail(user.getEmail());
        userServiceDto.setPassword(user.getPassword());
        userServiceDto.setRole(user.getRole());
        log.info("User transformed to UserDto");
        return userServiceDto;
    }

    public User toUserEntity (UserServiceDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        log.info("UserDto transformed to User");
        return user;
    }

    public OrderServiceDto toOrderDto (Order order) {
        OrderServiceDto orderServiceDto = new OrderServiceDto();
        orderServiceDto.setId(order.getId());
        orderServiceDto.setUser(order.getUser());
        orderServiceDto.setStatus(OrderServiceDto.Status.valueOf(order.getStatus().toString()));
        orderServiceDto.setTotalCost(orderItemRepository.findTotalCost(order));
        orderServiceDto.setItems(order.getItems());
        log.info("Order transformed to OrderServiceDto");
        return orderServiceDto;
    }
}
