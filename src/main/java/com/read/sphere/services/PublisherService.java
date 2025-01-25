package com.read.sphere.services;

import com.read.sphere.dtos.create.PublisherCreateDto;
import com.read.sphere.dtos.list.PublisherListDto;
import com.read.sphere.dtos.update.PublisherUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublisherService extends BaseCRUDService<PublisherListDto, PublisherCreateDto, PublisherUpdateDto>{
}
