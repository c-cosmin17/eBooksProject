package com.bookstore.be.model;


public class BookComplete {
    private int id;
    private String category;
    private String type;
    private String publisher;
    private String title;
    private String author;
    private float price;

    private String authorDescription;

    private String bookDescription;

    private String imagePath;
    private String pdfPath;

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public BookComplete() {
    }

    public BookComplete(int id, String category, String type, String publisher, String title, String author, float price, String authorDescription, String bookDescription) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.price = price;
        this.authorDescription = authorDescription;
        this.bookDescription = bookDescription;
    }
}
