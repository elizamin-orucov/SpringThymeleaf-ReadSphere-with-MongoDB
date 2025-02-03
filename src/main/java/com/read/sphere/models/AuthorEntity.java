package com.read.sphere.models;

import com.read.sphere.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "author")
public class AuthorEntity extends BaseEntity {
    @Field(name = "name")
    private String name;

    @Field(name = "surname")
    private String surname;

    @Field(name = "image_id")
    private String imageId;

    @Field(name = "author_about")
    private String about;
}
