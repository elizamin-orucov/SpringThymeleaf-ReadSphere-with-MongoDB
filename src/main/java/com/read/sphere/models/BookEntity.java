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
    @Field(name = "author_name")
    private String authorName;

    @Field(name = "book_code")
    @Indexed(unique = true)
    private String code;

    @DBRef
    private BookCategoryEntity category;

    @Field(name = "language")
    private String language;

    @Field(name = "published_date")
    private LocalDate publishedDate;

    @Field(name = "pages")
    private Integer pages;

    @Field(name = "image_id")
    private String imageId;

    @Field(name = "pdf_id")
    private String pdfId;
}

