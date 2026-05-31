package com.library.model;

public class Book extends LibraryItem {
    private String isbn;

    public Book(String id, String title, String author, String isbn) {
        super(id, title, author);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be empty.");
        }
        this.isbn = isbn;
    }

    @Override
    public String getCategory() {
        return "Book";
    }

    @Override
    public double calculateLateFee(int lateDays) {
        return Math.max(0, lateDays) * 10.0;
    }

    @Override
    public String getShortInfo() {
        return super.getShortInfo() + " | ISBN: " + isbn;
    }
}
