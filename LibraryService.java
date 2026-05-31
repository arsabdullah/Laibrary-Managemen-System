package com.library.service;

import com.library.model.LibraryItem;
import com.library.model.Member;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LibraryService implements Searchable {
    private final Map<String, LibraryItem> items;
    private final Map<String, Member> members;
    private final Map<String, String> issuedToMember;

    public LibraryService() {
        this.items = new LinkedHashMap<>();
        this.members = new LinkedHashMap<>();
        this.issuedToMember = new LinkedHashMap<>();
    }

    public void addItem(LibraryItem item) {
        if (items.containsKey(item.getId())) {
            throw new IllegalArgumentException("Item ID already exists.");
        }
        items.put(item.getId(), item);
    }

    public void addMember(Member member) {
        if (members.containsKey(member.getId())) {
            throw new IllegalArgumentException("Member ID already exists.");
        }
        members.put(member.getId(), member);
    }

    public List<LibraryItem> getAllItems() {
        return Collections.unmodifiableList(new ArrayList<>(items.values()));
    }

    public List<Member> getAllMembers() {
        return Collections.unmodifiableList(new ArrayList<>(members.values()));
    }

    public Optional<LibraryItem> findItemById(String itemId) {
        return Optional.ofNullable(items.get(itemId));
    }

    public Optional<Member> findMemberById(String memberId) {
        return Optional.ofNullable(members.get(memberId));
    }

    public void issueItem(String itemId, String memberId) {
        LibraryItem item = getRequiredItem(itemId);
        Member member = getRequiredMember(memberId);
        item.issue();
        member.borrowItem();
        issuedToMember.put(itemId, memberId);
    }

    public double returnItem(String itemId, int lateDays) {
        LibraryItem item = getRequiredItem(itemId);
        String memberId = issuedToMember.get(itemId);
        if (memberId == null) {
            throw new IllegalStateException("This item was not issued to any member.");
        }

        Member member = getRequiredMember(memberId);
        item.returnItem();
        member.returnBorrowedItem();
        issuedToMember.remove(itemId);
        return item.calculateLateFee(lateDays);
    }

    @Override
    public List<LibraryItem> searchByTitle(String title) {
        return search(title, true);
    }

    @Override
    public List<LibraryItem> searchByAuthor(String author) {
        return search(author, false);
    }

    private List<LibraryItem> search(String keyword, boolean byTitle) {
        String normalizedKeyword = keyword == null ? "" : keyword.toLowerCase();
        List<LibraryItem> results = new ArrayList<>();

        for (LibraryItem item : items.values()) {
            String value = byTitle ? item.getTitle() : item.getAuthor();
            if (value.toLowerCase().contains(normalizedKeyword)) {
                results.add(item);
            }
        }

        return results;
    }

    private LibraryItem getRequiredItem(String itemId) {
        LibraryItem item = items.get(itemId);
        if (item == null) {
            throw new IllegalArgumentException("Item not found.");
        }
        return item;
    }

    private Member getRequiredMember(String memberId) {
        Member member = members.get(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not found.");
        }
        return member;
    }
}
