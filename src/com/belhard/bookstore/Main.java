package com.belhard.bookstore;

import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.BookDaoImpl;
import com.belhard.bookstore.dao.DataSource;
import com.belhard.bookstore.entity.Book;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static String scan() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        return answer;
    }

    public static void requestMenu() {
        System.out.println("Please enter your request:");
        BookDao bookDao = new BookDaoImpl(new DataSource());
        String[] answer = scan().split(" ");
        Long id = null;
        if (answer.length > 1) {
            id = Long.valueOf(answer[1]);
        }
        switch (answer[0]) {
            case "all":
                List<Book> books = bookDao.findAll();
                books.forEach(value -> System.out.println(value.getId() + ". \"" + value.getTitle() + "\" by " +
                        value.getAuthor() + ", " + value.getPublishinYear() + " year"));
                requestMenu();
                break;
            case "get":
                System.out.println(bookDao.findById(id));
                requestMenu();
                break;
            case "delete":
                System.out.println(bookDao.delete(id));
                requestMenu();
                break;
            case "exit":
                System.out.println("Good bye!");
                break;
            default:
                System.out.println("Request not defined");
                break;
        }
    }

    public static void main(String[] args) {
        requestMenu();
    }
}