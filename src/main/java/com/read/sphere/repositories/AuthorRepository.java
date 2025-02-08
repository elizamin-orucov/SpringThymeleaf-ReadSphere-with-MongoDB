package com.read.sphere.repositories;

import com.read.sphere.models.AuthorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<AuthorEntity, String> {
}
