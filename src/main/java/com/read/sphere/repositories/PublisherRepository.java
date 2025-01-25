package com.read.sphere.repositories;

import com.read.sphere.dtos.create.PublisherCreateDto;
import com.read.sphere.dtos.list.PublisherListDto;
import com.read.sphere.dtos.update.PublisherUpdateDto;
import com.read.sphere.models.BookEntity;
import com.read.sphere.models.PublisherEntity;
import com.read.sphere.services.BaseCRUDService;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublisherRepository extends MongoRepository<PublisherEntity, String> {
}
