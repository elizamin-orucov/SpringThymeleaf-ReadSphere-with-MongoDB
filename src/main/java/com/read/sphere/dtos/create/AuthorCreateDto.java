package com.read.sphere.dtos.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class AuthorCreateDto {
    private String name;
    private String surname;
    private String about;

    public AuthorCreateDto() {
    }

    public AuthorCreateDto(String name, String surname, String about) {
        this.name = name;
        this.surname = surname;
        this.about = about;
    }
}

