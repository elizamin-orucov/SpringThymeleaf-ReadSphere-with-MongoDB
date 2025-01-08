package com.read.sphere.services.Impl;

import com.read.sphere.models.BookEntity;
import com.read.sphere.repositories.BookRepository;
import com.read.sphere.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<BookEntity> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return repository.findAll(pageable);
    }

    @Override
    public BookEntity findById(String id) {
        return repository.findById(id).orElse(null);
    }
}
