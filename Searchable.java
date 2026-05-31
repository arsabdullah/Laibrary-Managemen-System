package com.library.service;

import com.library.model.LibraryItem;
import java.util.List;

public interface Searchable {
    List<LibraryItem> searchByTitle(String title);

    List<LibraryItem> searchByAuthor(String author);
}
