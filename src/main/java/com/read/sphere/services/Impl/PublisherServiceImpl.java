package com.read.sphere.services.Impl;

import com.read.sphere.dtos.create.PublisherCreateDto;
import com.read.sphere.dtos.list.PublisherListDto;
import com.read.sphere.dtos.update.PublisherUpdateDto;
import com.read.sphere.models.PublisherEntity;
import com.read.sphere.repositories.PublisherRepository;
import com.read.sphere.services.PublisherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository repository;

    public PublisherServiceImpl(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<PublisherListDto> list(Pageable pageable) {
        return repository.findAll(pageable).map(PublisherServiceImpl::toPublisherListDto);
    }

    @Override
    public List<PublisherListDto> list() {
        return repository.findAll().stream().map(PublisherServiceImpl::toPublisherListDto).toList();
    }

    @Override
    public String create(PublisherCreateDto publisherCreateDto) {
        PublisherEntity entity = toPublisherEntity(publisherCreateDto);
        repository.save(entity);
        return "new publisher successfully created";
    }

    @Override
    public String update(PublisherUpdateDto publisherUpdateDto) {
        PublisherEntity entity = toPublisherEntity(publisherUpdateDto);
        repository.save(entity);
        return "publisher updated successfully";
    }

    @Override
    public String delete(String id) {
        return "";
    }

    public static PublisherListDto toPublisherListDto(PublisherEntity entity){
        return new PublisherListDto(
                entity.getId(), entity.getPublisherName(), entity.getImageId()
        );
    }

    public static PublisherEntity toPublisherEntity(PublisherUpdateDto updateDto){
        return new PublisherEntity(
                updateDto.getId(), updateDto.getPublisherName(), updateDto.getImageId()
        );
    }

    public static PublisherEntity toPublisherEntity(PublisherCreateDto createDto){
        return new PublisherEntity(
                createDto.getId(), createDto.getPublisherName(), createDto.getImageId()
        );
    }
}
