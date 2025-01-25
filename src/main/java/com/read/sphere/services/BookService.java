package com.read.sphere.services;

import com.read.sphere.dtos.create.BookCreateDto;
import com.read.sphere.dtos.list.BookListDto;
import com.read.sphere.dtos.update.BookUpdateDto;
import org.springframework.web.multipart.MultipartFile;

public interface BookService extends BaseCRUDService<BookListDto, BookCreateDto, BookUpdateDto>{
    String create(BookCreateDto bookCreateDto, MultipartFile image, MultipartFile pdf);
}
