package com.library.model;

public class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String id, String title, String author, int issueNumber) {
        super(id, title, author);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        if (issueNumber <= 0) {
            throw new IllegalArgumentException("Issue number must be positive.");
        }
        this.issueNumber = issueNumber;
    }

    @Override
    public String getCategory() {
        return "Magazine";
    }

    @Override
    public double calculateLateFee(int lateDays) {
        return Math.max(0, lateDays) * 5.0;
    }

    @Override
    public String getShortInfo() {
        return super.getShortInfo() + " | Issue: " + issueNumber;
    }
}
