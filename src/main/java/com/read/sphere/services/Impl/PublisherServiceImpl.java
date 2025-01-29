package com.read.sphere.services.Impl;

import com.read.sphere.dtos.create.PublisherCreateDto;
import com.read.sphere.dtos.list.PublisherListDto;
import com.read.sphere.dtos.update.PublisherUpdateDto;
import com.read.sphere.exception.EntityNotFoundException;
import com.read.sphere.models.PublisherEntity;
import com.read.sphere.repositories.PublisherRepository;
import com.read.sphere.services.MediaService;
import com.read.sphere.services.PublisherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository repository;
    private final MediaService mediaService;

    public PublisherServiceImpl(PublisherRepository repository, MediaService mediaService) {
        this.repository = repository;
        this.mediaService = mediaService;
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
    public String create(PublisherCreateDto publisherCreateDto, MultipartFile image) {
        PublisherEntity entity = toPublisherEntity(publisherCreateDto);
        PublisherEntity savedEntity = repository.save(entity);
        return "new publisher successfully created";
    }

    @Override
    public PublisherEntity getById(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Publisher not found with ID: " + id));
    }

    @Override
    public String update(PublisherUpdateDto publisherUpdateDto) {
        PublisherEntity entity = toPublisherEntity(publisherUpdateDto);
        repository.save(entity);
        return "publisher updated successfully";
    }

    @Override
    public String delete(String id) {
        String message = id + " ID not found";

        Optional<PublisherEntity> publisherEntityOptional = repository.findById(id);

        if (publisherEntityOptional.isPresent()){
            PublisherEntity publisherEntity = publisherEntityOptional.get();
            mediaService.deleteFile(publisherEntity.getImageId());
            repository.delete(publisherEntity);
            message = id + " successfully deleted";
        }
        return message;
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
                createDto.getPublisherName()
        );
    }
}
