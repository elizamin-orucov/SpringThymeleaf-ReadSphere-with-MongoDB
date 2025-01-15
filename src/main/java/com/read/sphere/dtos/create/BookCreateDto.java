package com.read.sphere.dtos.create;

import com.read.sphere.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class BookCreateDto extends BaseDto {
    private String bookName;

    private String authorName;

    private String description;

    private String categoryId;

    private String publisher;

    private String language;

    private LocalDate publishedDate;

    private Integer releaseYear;

    private Integer pages;
}
