package com.company.book;

public class Book {
    public String welcome( ) {
        return "Welcome to my new book.";
    }
}

// javac -d mods/com.company.book mods/com.company.module-info.java mods/com.company.book/Book.java
// java --module-path mods -m com.company.book/com.company.book/Book.java

// JAR - module-info.class

// JMOD