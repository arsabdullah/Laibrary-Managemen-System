package com.library.app;

import com.library.model.Book;
import com.library.model.Librarian;
import com.library.model.LibraryItem;
import com.library.model.Magazine;
import com.library.model.Member;
import com.library.service.LibraryService;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private final Scanner scanner;
    private final LibraryService libraryService;

    public LibraryApp() {
        this.scanner = new Scanner(System.in);
        this.libraryService = new LibraryService();
        loadDemoData();
    }

    public static void main(String[] args) {
        new LibraryApp().run();
    }

    private void run() {
        System.out.println("=================================");
        System.out.println(" Java OOP Library Management App ");
        System.out.println("=================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose option: ");

            try {
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> addMagazine();
                    case 3 -> addMember();
                    case 4 -> showItems();
                    case 5 -> showMembers();
                    case 6 -> issueItem();
                    case 7 -> returnItem();
                    case 8 -> searchItems();
                    case 9 -> showOopPillars();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        System.out.println("Thank you for using the system.");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Add Book");
        System.out.println("2. Add Magazine");
        System.out.println("3. Add Member");
        System.out.println("4. Show Library Items");
        System.out.println("5. Show Members");
        System.out.println("6. Issue Item");
        System.out.println("7. Return Item");
        System.out.println("8. Search Items");
        System.out.println("9. Show OOP 4 Pillars Used");
        System.out.println("0. Exit");
    }

    private void addBook() {
        String id = readText("Book ID: ");
        String title = readText("Title: ");
        String author = readText("Author: ");
        String isbn = readText("ISBN: ");
        libraryService.addItem(new Book(id, title, author, isbn));
        System.out.println("Book added successfully.");
    }

    private void addMagazine() {
        String id = readText("Magazine ID: ");
        String title = readText("Title: ");
        String author = readText("Author/Publisher: ");
        int issue = readInt("Issue number: ");
        libraryService.addItem(new Magazine(id, title, author, issue));
        System.out.println("Magazine added successfully.");
    }

    private void addMember() {
        String id = readText("Member ID: ");
        String name = readText("Member name: ");
        libraryService.addMember(new Member(id, name));
        System.out.println("Member added successfully.");
    }

    private void showItems() {
        List<LibraryItem> items = libraryService.getAllItems();
        if (items.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        items.forEach(item -> System.out.println(item.getShortInfo()));
    }

    private void showMembers() {
        List<Member> members = libraryService.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }
        members.forEach(member -> System.out.println(member.getProfile()));
    }

    private void issueItem() {
        String itemId = readText("Item ID: ");
        String memberId = readText("Member ID: ");
        libraryService.issueItem(itemId, memberId);
        System.out.println("Item issued successfully.");
    }

    private void returnItem() {
        String itemId = readText("Item ID: ");
        int lateDays = readInt("Late days: ");
        double fee = libraryService.returnItem(itemId, lateDays);
        System.out.printf("Item returned successfully. Late fee: %.2f BDT%n", fee);
    }

    private void searchItems() {
        System.out.println("1. Search by title");
        System.out.println("2. Search by author");
        int choice = readInt("Choose option: ");
        String keyword = readText("Keyword: ");

        List<LibraryItem> results = switch (choice) {
            case 1 -> libraryService.searchByTitle(keyword);
            case 2 -> libraryService.searchByAuthor(keyword);
            default -> throw new IllegalArgumentException("Invalid search option.");
        };

        if (results.isEmpty()) {
            System.out.println("No matching item found.");
            return;
        }
        results.forEach(item -> System.out.println(item.getShortInfo()));
    }

    private void showOopPillars() {
        System.out.println("Encapsulation: private fields with getters/setters in model classes.");
        System.out.println("Inheritance: Book and Magazine extend LibraryItem; Member extends User.");
        System.out.println("Polymorphism: calculateLateFee() behaves differently for Book and Magazine.");
        System.out.println("Abstraction: abstract classes LibraryItem/User and interface Searchable.");
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }
        return input;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void loadDemoData() {
        Librarian librarian = new Librarian("L1", "Admin Librarian", "EMP-1001");
        libraryService.addItem(new Book("B1", "Effective Java", "Joshua Bloch", "9780134685991"));
        libraryService.addItem(new Book("B2", "Clean Code", "Robert C. Martin", "9780132350884"));
        libraryService.addItem(new Magazine("M1", "Science Today", "Science Press", 42));
        libraryService.addMember(new Member("U1", "Abdullah"));

        System.out.println("Logged in as: " + librarian.getProfile());
    }
}
