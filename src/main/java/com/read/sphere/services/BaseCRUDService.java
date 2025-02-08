package com.read.sphere.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseCRUDService<
        EntityListDto, EntityCreateDto, EntityUpdateDto
        > {

    Page<EntityListDto> list(Pageable pageable);

    List<EntityListDto> list();

    String create(EntityCreateDto dto);

    String update(EntityUpdateDto dto);

    String delete(String id);

}
