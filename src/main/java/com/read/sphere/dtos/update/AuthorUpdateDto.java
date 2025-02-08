package com.read.sphere.dtos.update;

import com.read.sphere.dtos.create.AuthorCreateDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorUpdateDto extends AuthorCreateDto {
    private String bookId;
}
