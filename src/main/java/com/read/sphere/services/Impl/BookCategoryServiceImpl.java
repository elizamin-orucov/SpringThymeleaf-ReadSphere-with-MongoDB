package com.read.sphere.services.Impl;

import com.read.sphere.dtos.create.BookCategoryCreateDto;
import com.read.sphere.dtos.list.BookCategoryListDto;
import com.read.sphere.dtos.update.BookCategoryUpdateDto;
import com.read.sphere.models.BookCategoryEntity;
import com.read.sphere.repositories.BookCategoryRepository;
import com.read.sphere.services.BookCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    private final BookCategoryRepository repository;

    public BookCategoryServiceImpl(BookCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<BookCategoryListDto> list(Pageable pageable) {
        Page<BookCategoryEntity> categoryListDtos = repository.findAll(pageable);
        return categoryListDtos.map(BookCategoryServiceImpl::toBookCategoryListDto);
    }

    public List<BookCategoryListDto> list() {
        return repository.findAll().stream().map(BookCategoryServiceImpl::toBookCategoryListDto).toList();
    }

    @Override
    public String create(BookCategoryCreateDto bookCategoryCreateDto) {
        BookCategoryEntity bookCategoryEntity = toBookCategoryEntity(bookCategoryCreateDto);
        repository.save(bookCategoryEntity);
        return "new category added successfully";
    }

    @Override
    public String update(BookCategoryUpdateDto bookCategoryUpdateDto) {
        BookCategoryEntity bookCategoryEntity = toBookCategoryEntity(bookCategoryUpdateDto);
        repository.save(bookCategoryEntity);
        return "category successfully updated";
    }

    @Override
    public String delete(String id) {
        if (repository.findById(id).isEmpty()){
            return "id not found";
        }
        else {
            repository.deleteById(id);
            return "deletion successful";
        }
    }

    public static <T extends BookCategoryListDto> BookCategoryEntity toBookCategoryEntity(T dto){
        return new BookCategoryEntity(dto.getId(), dto.getCategoryName());
    }

    public static <T extends BookCategoryEntity> BookCategoryListDto toBookCategoryListDto(T entity){
        return new BookCategoryListDto(entity.getId(), entity.getCategoryName());
    }
}
