package com.example.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un livre dans la bibliothèque.
 * Contient des informations telles que le titre, l'auteur et l'année de publication.
 */
class Book {
    private String title;
    private String author;
    private int year;

    /**
     * Constructeur pour initialiser un livre.
     *
     * @param title  Le titre du livre.
     * @param author L'auteur du livre.
     * @param year   L'année de publication.
     */
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    /**
     * Retourne le titre du livre.
     *
     * @return Le titre du livre.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retourne l'auteur du livre.
     *
     * @return L'auteur du livre.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Retourne l'année de publication.
     *
     * @return L'année de publication.
     */
    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}