package com.read.sphere.dtos.list;

import com.read.sphere.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookListDto extends BaseDto {

    private String bookName;

    private String authorName;

    private String description;

    private String categoryName;

    private String publisherName;

    private Integer releaseYear;

    private String imageId;

    public BookListDto() {
    }

    public BookListDto(
            String id,
            String bookName,
            String authorName,
            String description,
            String categoryName,
            String publisherName,
            Integer releaseYear,
            String imageId
    ) {
        super(id);
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.categoryName = categoryName;
        this.publisherName = publisherName;
        this.releaseYear = releaseYear;
        this.imageId = imageId;
    }
}
