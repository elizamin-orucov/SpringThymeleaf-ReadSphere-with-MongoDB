package com.read.sphere.repositories;

import com.read.sphere.models.BookCategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookCategoryRepository extends MongoRepository<BookCategoryEntity, String> {
}
