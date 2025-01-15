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

}
