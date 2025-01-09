package com.read.sphere.dtos.list;

import com.read.sphere.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookCategoryListDto extends BaseDto {
    private String categoryName;

    public BookCategoryListDto() {
    }
    public BookCategoryListDto(String id, String categoryName) {
        super(id);
        this.categoryName = categoryName;
    }
}
