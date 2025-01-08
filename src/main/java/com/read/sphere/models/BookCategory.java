package com.read.sphere.models;

import com.read.sphere.base.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "book_category")
public class BookCategory extends BaseEntity {
    @Field(name = "category_name")
    private String categoryName;
}

