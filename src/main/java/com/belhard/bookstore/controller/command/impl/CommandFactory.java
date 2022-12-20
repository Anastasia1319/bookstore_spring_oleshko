package com.belhard.bookstore.controller.command.impl;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.controller.command.impl.book.BookCommand;
import com.belhard.bookstore.controller.command.impl.book.BooksCommand;
import com.belhard.bookstore.controller.command.impl.user.UserCommand;
import com.belhard.bookstore.data.connection.DataSource;
import com.belhard.bookstore.data.dao.impl.BookDaoImpl;
import com.belhard.bookstore.data.dao.impl.UserDaoImpl;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.impl.BookServiceImpl;
import com.belhard.bookstore.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> commands;
    public static final CommandFactory INSTANCE = new CommandFactory();

    private CommandFactory() {
        BookService bookService = new BookServiceImpl(new BookDaoImpl(new DataSource()));
        UserService userService = new UserServiceImpl(new UserDaoImpl(new DataSource()));
        commands = new HashMap<>();
        commands.put("book", new BookCommand(bookService));
        commands.put("error", new ErrorCommand());
        commands.put("books", new BooksCommand(bookService));
        commands.put("users", new UserCommand(userService));
    }

    public Command getCommand(String action) {
        Command controller = commands.get(action);
        if (controller == null) {
            controller = commands.get("error");
        }
        return controller;
    }
}
