import java.util.*;

class Book {
    int id;
    String name, author;
    boolean isAvailable;

    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isAvailable = true;
    }
}

public class LibraryManagement {
    static List<Book> books = new ArrayList<>();
    static Map<Integer, String> issuedBooks = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static int bookIdCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addBook(); break;
                case 2: viewBooks(); break;
                case 3: issueBook(); break;
                case 4: returnBook(); break;
                case 5: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    static void addBook() {
        System.out.print("Enter book name: ");
        String name = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        books.add(new Book(bookIdCounter++, name, author));
        System.out.println("Book added successfully!");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\nBook List:");
        for (Book book : books) {
            System.out.println("ID: " + book.id + ", Name: " + book.name + ", Author: " + book.author + ", Available: " + book.isAvailable);
        }
    }

    static void issueBook() {
        System.out.print("Enter book ID to issue: ");
        int id = scanner.nextInt();
        for (Book book : books) {
            if (book.id == id && book.isAvailable) {
                book.isAvailable = false;
                issuedBooks.put(id, book.name);
                System.out.println("Book issued successfully!");
                return;
            }
        }
        System.out.println("Book not available or invalid ID!");
    }

    static void returnBook() {
        System.out.print("Enter book ID to return: ");
        int id = scanner.nextInt();
        if (issuedBooks.containsKey(id)) {
            for (Book book : books) {
                if (book.id == id) {
                    book.isAvailable = true;
                    issuedBooks.remove(id);
                    System.out.println("Book returned successfully!");
                    return;
                }
            }
        } else {
            System.out.println("Invalid Book ID!");
        }
    }
}
