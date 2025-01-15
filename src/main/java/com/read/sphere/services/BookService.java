package com.read.sphere.services;

import com.read.sphere.dtos.create.BookCreateDto;
import com.read.sphere.dtos.list.BookListDto;
import com.read.sphere.dtos.update.BookUpdateDto;

public interface BookService extends BaseCRUDService<BookListDto, BookCreateDto, BookUpdateDto>{
}
