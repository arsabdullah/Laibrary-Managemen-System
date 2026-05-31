package com.library.model;

public abstract class LibraryItem {
    private final String id;
    private String title;
    private String author;
    private boolean issued;

    protected LibraryItem(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be empty.");
        }
        this.author = author;
    }

    public boolean isIssued() {
        return issued;
    }

    public void issue() {
        if (issued) {
            throw new IllegalStateException("Item is already issued.");
        }
        issued = true;
    }

    public void returnItem() {
        if (!issued) {
            throw new IllegalStateException("Item is not issued.");
        }
        issued = false;
    }

    public abstract String getCategory();

    public abstract double calculateLateFee(int lateDays);

    public String getShortInfo() {
        return id + " | " + title + " | " + author + " | " + getCategory()
                + " | " + (issued ? "Issued" : "Available");
    }
}
