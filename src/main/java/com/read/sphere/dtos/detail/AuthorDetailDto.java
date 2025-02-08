package com.read.sphere.dtos.detail;

import com.read.sphere.dtos.create.AuthorCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class AuthorDetailDto extends AuthorCreateDto {
    private String id;
    private String imageId;

    public AuthorDetailDto() {
        super();
    }

    public AuthorDetailDto(String id, String name, String surname, String about, String imageId) {
        super(name, surname, about);
        this.id = id;
        this.imageId = imageId;
    }
}
