package com.read.sphere.dtos.detail;

import com.read.sphere.dtos.list.BookListDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDetailDto extends BookListDto {

    public BookDetailDto() {
    }

    public BookDetailDto(
            String id,
            String bookName,
            String authorName,
            String description,
            String categoryName,
            String publisherName,
            Integer releaseYear,
            String imageId
    ) {
        super(
                id,
                bookName,
                authorName,
                description,
                categoryName,
                publisherName,
                releaseYear,
                imageId);
    }
}
