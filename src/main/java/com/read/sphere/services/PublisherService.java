package com.read.sphere.services;

import com.read.sphere.dtos.create.PublisherCreateDto;
import com.read.sphere.dtos.list.PublisherListDto;
import com.read.sphere.dtos.update.PublisherUpdateDto;
import com.read.sphere.models.PublisherEntity;
import org.springframework.web.multipart.MultipartFile;

public interface PublisherService extends BaseCRUDService<PublisherListDto, PublisherCreateDto, PublisherUpdateDto>{
    String create(PublisherCreateDto publisherCreateDto, MultipartFile image);

    PublisherEntity getById(String id);
}
