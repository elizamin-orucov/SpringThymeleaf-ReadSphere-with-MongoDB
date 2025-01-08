package com.read.sphere.repositories;

import com.read.sphere.models.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookEntity, String> {
}
