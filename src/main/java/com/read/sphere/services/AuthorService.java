package com.read.sphere.services;

import com.read.sphere.dtos.create.AuthorCreateDto;
import com.read.sphere.dtos.detail.AuthorDetailDto;
import com.read.sphere.dtos.list.AuthorListDto;
import com.read.sphere.dtos.update.AuthorUpdateDto;
import com.read.sphere.models.AuthorEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AuthorService extends BaseCRUDService<AuthorListDto, AuthorCreateDto, AuthorUpdateDto> {
    String create(AuthorCreateDto bookCreateDto, MultipartFile image);

    AuthorDetailDto findById(String id);

    AuthorEntity getById(String id);
}
