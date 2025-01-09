package com.read.sphere.base;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseDto {
    private String id;

    public BaseDto() {
    }

    public BaseDto(String id) {
        this.id = id;
    }
}
