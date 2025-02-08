package com.read.sphere.services.Impl;

import com.read.sphere.dtos.create.AuthorCreateDto;
import com.read.sphere.dtos.create.BookCreateDto;
import com.read.sphere.dtos.detail.AuthorDetailDto;
import com.read.sphere.dtos.list.AuthorListDto;
import com.read.sphere.dtos.update.AuthorUpdateDto;
import com.read.sphere.exception.EntityNotFoundException;
import com.read.sphere.models.AuthorEntity;
import com.read.sphere.models.BookEntity;
import com.read.sphere.repositories.AuthorRepository;
import com.read.sphere.services.AuthorService;
import com.read.sphere.services.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final MediaService mediaService;

    @Value("${baseFolder}")
    private String baseFolder;

    public AuthorServiceImpl(AuthorRepository authorRepository, MediaService mediaService) {
        this.authorRepository = authorRepository;
        this.mediaService = mediaService;
    }

    @Override
    public String create(AuthorCreateDto bookCreateDto, MultipartFile image) {
        AuthorEntity entity = toEntity(bookCreateDto);

        AuthorEntity savedEntity = authorRepository.save(entity);

        String saveFolder = baseFolder + "author/images/" + savedEntity.getId() + "/";
        String imagePath = mediaService.saveFile(saveFolder, image);

        savedEntity.setImageId(imagePath);

        authorRepository.save(savedEntity);

        return "author added successfully";
    }

    @Override
    public AuthorDetailDto findById(String id) {
        AuthorEntity entity = getById(id);
        return toDetailDto(entity);
    }

    @Override
    public AuthorEntity getById(String id) {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + id));
    }

    @Override
    public Page<AuthorListDto> list(Pageable pageable) {
        Page<AuthorEntity> entities = authorRepository.findAll(pageable);
        return entities.map(AuthorServiceImpl::toListDto);
    }

    @Override
    public List<AuthorListDto> list() {
        return authorRepository.findAll().stream().map(AuthorServiceImpl::toListDto).toList();
    }

    @Override
    public String create(AuthorCreateDto authorCreateDto) {
        AuthorEntity entity = toEntity(authorCreateDto);
        authorRepository.save(entity);
        return "new author added successfully";
    }

    @Override
    public String update(AuthorUpdateDto authorUpdateDto) {
        AuthorEntity entity = toEntity(authorUpdateDto);
        authorRepository.save(entity);
        return "author successfully updated";
    }

    @Override
    public String delete(String id) {
        String message = id + " ID not found";

        Optional<AuthorEntity> entity = authorRepository.findById(id);
        if (entity.isPresent()){
            AuthorEntity entity1 = entity.get();
            mediaService.deleteFile(entity1.getImageId());
            authorRepository.delete(entity1);
            message = id + " successfully deleted";
        }

        log.info("Book delete: {} ", message);
        return message;
    }

    private static String getFullName(String name, String surname){
        // create full name for author
        return name.concat(" ").concat(surname);
    }

    public static AuthorEntity toEntity(AuthorCreateDto createDto){
        return new AuthorEntity(createDto.getName(), createDto.getSurname(), createDto.getAbout());
    }

    public static AuthorEntity toEntity(AuthorUpdateDto updateDto){
        return new AuthorEntity(updateDto.getBookId(), updateDto.getName(), updateDto.getSurname(), updateDto.getAbout());
    }

    public static AuthorListDto toListDto(AuthorEntity entity){
        return new AuthorListDto(entity.getId(), getFullName(entity.getName(), entity.getSurname()), entity.getImageId());
    }

    public static AuthorDetailDto toDetailDto(AuthorEntity entity){
        return new AuthorDetailDto(entity.getId(), entity.getName(), entity.getSurname(), entity.getAbout(), entity.getImageId());
    }

}
