package com.bookstore.be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private int id;
    private int categoryId;
    private int typeId;
    private int publisherId;
    private String title;
    private String author;
    private float price;
    @Column(length = 1000)
    private String authorDescription;
    @Column(length = 1000)
    private String bookDescription;
    private String imagePath;
    private String pdfPath;

}
