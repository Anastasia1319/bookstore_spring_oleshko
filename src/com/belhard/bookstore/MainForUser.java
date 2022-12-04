package com.belhard.bookstore;

import com.belhard.bookstore.data.connection.DataSource;
import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.dao.impl.UserDaoImpl;
import com.belhard.bookstore.data.entity.User;

import java.util.List;
import java.util.Scanner;

public class MainForUser {

    private static String scan() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        return answer;
    }
    public static void requestMenu() {
        System.out.println("Please enter your request:");
        UserDao userDao = new UserDaoImpl(new DataSource());
        String[] answer = scan().split(" ");
        Long id = null;
        if (answer.length > 1) {
            id = Long.valueOf(answer[1]);
        }
        switch (answer[0]) {
            case "all":
                List<User> users = userDao.findAll();
                users.stream()
                        .forEach(value -> System.out.println(value.getId() + ". " + value.getFirstName() + " " +
                        value.getLastName() + ", " + value.getRole()));
                requestMenu();
                break;
            case "get":
                System.out.println(userDao.findById(id));
                requestMenu();
                break;
            case "delete":
                System.out.println(userDao.delete(id));
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
