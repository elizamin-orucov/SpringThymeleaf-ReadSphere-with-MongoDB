package com.read.sphere.services;

import com.read.sphere.models.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<BookEntity> getAllBooks(int page, int size);

    BookEntity findById(String id);


}
