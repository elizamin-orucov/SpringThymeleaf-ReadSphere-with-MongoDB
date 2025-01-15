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
}
