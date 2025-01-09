package com.read.sphere.models;

import com.read.sphere.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@Document(collection = "book_category")
public class BookCategoryEntity extends BaseEntity {
    @Field(name = "category_name")
    private String categoryName;

    public BookCategoryEntity() {
    }

    public BookCategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }

    public BookCategoryEntity(String id, String categoryName) {
        super(id);
        this.categoryName = categoryName;
    }
}


