package com.read.sphere.models;

import com.read.sphere.base.BaseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "publisher")
public class PublisherEntity extends BaseDto {
    private String publisherName;

    private String imageId;

    public PublisherEntity() {
    }

    public PublisherEntity(String publisherName) {
        this.publisherName = publisherName;
    }

    public PublisherEntity(String publisherName, String imageId) {
        super();
        this.publisherName = publisherName;
        this.imageId = imageId;
    }

    public PublisherEntity(String id, String publisherName, String imageId) {
        super(id);
        this.publisherName = publisherName;
        this.imageId = imageId;
    }
}

