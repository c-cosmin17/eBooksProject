package com.bookstore.be.model;

import jakarta.persistence.Column;

public class UpdateBook {

    private int categoryId;
    private int typeId;
    private int publisherId;
    private String title;
    private String author;
    private float price;

    private String authorDescription;

    private String bookDescription;

    public UpdateBook() {
    }

    public UpdateBook(int categoryId, int typeId, int publisherId, String title, String author, float price, String authorDescription, String bookDescription) {

        this.categoryId = categoryId;
        this.typeId = typeId;
        this.publisherId = publisherId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.authorDescription = authorDescription;
        this.bookDescription = bookDescription;
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthorDescription() {
        return authorDescription;
    }

    public void setAuthorDescription(String authorDescription) {
        this.authorDescription = authorDescription;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}
