package com.read.sphere.models;

import com.read.sphere.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "book")
public class BookEntity extends BaseEntity {
    @Field(name = "book_name")
    private String bookName;

    @Field(name = "author_name")
    private String authorName;

    @Field(name = "description")
    private String description;

    @Field(name = "book_code")
    @Indexed(unique = true)
    private String code;

    @DBRef
    private BookCategoryEntity category;

    @DBRef
    private PublisherEntity publisher;

    @Field(name = "language")
    private String language;

    @Field(name = "published_date")
    private LocalDate publishedDate;

    @Field(name = "release_year")
    private Integer releaseYear;

    @Field(name = "pages")
    private Integer pages;

    @Field(name = "image_id")
    private String imageId;

    @Field(name = "pdf_id")
    private String pdfId;

    // constructors
    public BookEntity() {
    }

    public BookEntity(
            String id,
            String bookName,
            String authorName,
            String description,
            String language,
            LocalDate publishedDate,
            Integer releaseYear,
            Integer pages
    ) {
        super(id);
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.language = language;
        this.publishedDate = publishedDate;
        this.releaseYear = releaseYear;
        this.pages = pages;
    }
}

