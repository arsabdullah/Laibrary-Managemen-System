# Java OOP Library Management System

This is a complete console-based Java project that demonstrates the 4 pillars of Object-Oriented Programming.

## Features

- Add books and magazines
- Add library members
- Show all library items
- Show all members
- Issue an item to a member
- Return an item and calculate late fee
- Search items by title or author
- View where the 4 OOP pillars are used

## OOP 4 Pillars Used

1. Encapsulation
   - Fields are private in classes like `LibraryItem`, `Book`, `User`, and `Member`.
   - Data is accessed through getters and setters.

2. Inheritance
   - `Book` and `Magazine` extend `LibraryItem`.
   - `Member` and `Librarian` extend `User`.

3. Polymorphism
   - `calculateLateFee()` is implemented differently in `Book` and `Magazine`.
   - `getShortInfo()` is overridden to show item-specific details.

4. Abstraction
   - `LibraryItem` and `User` are abstract classes.
   - `Searchable` is an interface implemented by `LibraryService`.

## Project Structure

```text
src/
  com/library/app/LibraryApp.java
  com/library/model/
    Book.java
    Librarian.java
    LibraryItem.java
    Magazine.java
    Member.java
    User.java
  com/library/service/
    LibraryService.java
    Searchable.java
```

## How To Run

From the project folder:

```bash
javac -d out src/com/library/model/*.java src/com/library/service/*.java src/com/library/app/*.java
java -cp out com.library.app.LibraryApp
```

## Demo Data

The app starts with:

- Books: `B1`, `B2`
- Magazine: `M1`
- Member: `U1`
- Librarian: `L1`
