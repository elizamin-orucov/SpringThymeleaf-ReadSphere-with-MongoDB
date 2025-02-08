package com.read.sphere.dtos.list;

import com.read.sphere.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class AuthorListDto extends BaseDto {
    private String fullName;
    private String imageId;

    public AuthorListDto() {
    }

    public AuthorListDto(String id, String fullName, String imageId) {
        super(id);
        this.fullName = fullName;
        this.imageId = imageId;
    }

    public AuthorListDto(String id, String fullName) {
        super(id);
        this.fullName = fullName;
    }
}
