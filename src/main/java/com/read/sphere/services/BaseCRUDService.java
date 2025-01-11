package com.read.sphere.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseCRUDService<ListDto, CreateDto, UpdateDto> {

    Page<ListDto> list(Pageable pageable);

    List<ListDto> list();

    String create(CreateDto dto);

    String update(UpdateDto dto);

    String delete(String id);

}
