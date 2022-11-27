import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Please enter your request:");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        BookDao bookDao = new BookDaoImpl();
        long id;

        switch (answer) {
            case "all":
                List<Book> books = bookDao.findAll();
                books.forEach(value -> System.out.println(value.getId() + ". " + value.getTitle()+ " " + value.getAuthor()+ ", "
                        + value.getPublishinYear() + " year"));
                break;
            case "get":
                id = scanner.nextLong();
                System.out.println(bookDao.findById(id));
                break;
            case "delete":
                id = scanner.nextLong();
                System.out.println(bookDao.delete(id));
                break;
            case "exit":
                System.out.println("Good bye!");
                break;
        }
    }
}