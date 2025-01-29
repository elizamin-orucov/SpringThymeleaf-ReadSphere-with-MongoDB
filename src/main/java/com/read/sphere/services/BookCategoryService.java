package com.read.sphere.services;

import com.read.sphere.dtos.create.BookCategoryCreateDto;
import com.read.sphere.dtos.list.BookCategoryListDto;
import com.read.sphere.dtos.update.BookCategoryUpdateDto;
import com.read.sphere.models.BookCategoryEntity;

public interface BookCategoryService extends BaseCRUDService<BookCategoryListDto, BookCategoryCreateDto, BookCategoryUpdateDto>{
    BookCategoryEntity getById(String id);
}


