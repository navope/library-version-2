package ru.navope.rento.models;

import javax.validation.constraints.*;

public class Book {
    private int id;

    @NotEmpty(message = "Book's name should not be empty")
    @Pattern(regexp = "[A-Z][A-Za-z]*.*", message = "The first word of the book title begins with a " +
            "capital letter and consists only of letters, and all subsequent characters can be any")
    private String name;

    @NotEmpty(message = "Author's name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "The author's name should consist only of letters " +
            "and match such a pattern: 'Name Surname'")
    private String author;

    @Min(value = 1,message = "Year must be greater than 0")
    private int year;

    private Integer personId;

    public Book(int id ,String name, String author, int year, Integer personId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
