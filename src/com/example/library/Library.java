package com.example.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale pour gérer une bibliothèque.
 * Fournit des méthodes pour ajouter des livres, rechercher des livres,
 * et afficher la liste des livres disponibles.
 */
public class Library {
    private List<Book> books;

    /**
     * Constructeur pour initialiser la bibliothèque.
     */
    public Library() {
        this.books = new ArrayList<>();
    }

    /**
     * Ajoute un livre à la bibliothèque.
     *
     * @param book Le livre à ajouter.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Recherche des livres par titre.
     *
     * @param title Le titre du livre recherché.
     * @return Une liste de livres correspondant au titre.
     */
    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Affiche tous les livres disponibles dans la bibliothèque.
     */
    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

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
