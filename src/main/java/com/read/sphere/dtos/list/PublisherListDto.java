package com.read.sphere.dtos.list;

import com.read.sphere.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PublisherListDto extends BaseDto {
    private String publisherName;
    private String imageId;

    public PublisherListDto() {
    }

    public PublisherListDto(String publisherName, String imageId) {
        this.publisherName = publisherName;
        this.imageId = imageId;
    }

    public PublisherListDto(String id, String publisherName, String imageId) {
        super(id);
        this.publisherName = publisherName;
        this.imageId = imageId;
    }
}
