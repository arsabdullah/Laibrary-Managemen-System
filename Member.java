package com.library.model;

public class Member extends User {
    private int borrowedCount;

    public Member(String id, String name) {
        super(id, name);
        this.borrowedCount = 0;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void borrowItem() {
        borrowedCount++;
    }

    public void returnBorrowedItem() {
        if (borrowedCount > 0) {
            borrowedCount--;
        }
    }

    @Override
    public String getRole() {
        return "Member";
    }

    @Override
    public String getProfile() {
        return super.getProfile() + " | Borrowed: " + borrowedCount;
    }
}
