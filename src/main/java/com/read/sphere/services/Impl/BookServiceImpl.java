package com.read.sphere.services.Impl;

import com.read.sphere.dtos.create.BookCreateDto;
import com.read.sphere.dtos.list.BookListDto;
import com.read.sphere.dtos.update.BookUpdateDto;
import com.read.sphere.models.BookEntity;
import com.read.sphere.repositories.BookRepository;
import com.read.sphere.services.BookService;
import com.read.sphere.services.MediaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final MediaService mediaService;

    @Value("${baseFolder}")
    private String baseFolder;

    public BookServiceImpl(
            BookRepository repository,
            MediaService service
    ) {
        this.repository = repository;
        this.mediaService = service;
    }

    @Override
    public Page<BookListDto> list(Pageable pageable) {
        Page<BookEntity> bookEntities = repository.findAll(pageable);
        return bookEntities.map(BookServiceImpl::toBookListDto);
    }

    @Override
    public List<BookListDto> list() {
        List<BookEntity> bookEntities = repository.findAll();
        return bookEntities.stream().map(BookServiceImpl::toBookListDto).toList();
    }

    @Override
    public String create(BookCreateDto bookCreateDto) {
        BookEntity entity = toBookEntity(bookCreateDto);

        System.out.println("\n\nentity test");
        System.out.println(entity);
        System.out.println("---------------");
        System.out.println(entity instanceof BookEntity);
        System.out.println("\ntest\n\n");

        repository.save(entity);
        return "new book successfully created";
    }

    public String create(BookCreateDto bookCreateDto, MultipartFile image, MultipartFile pdf){
        System.out.println("service deki impl create method");
        System.out.println(baseFolder);
        BookEntity newBookEntity = toBookEntity(bookCreateDto);

        BookEntity savedBookEntity = repository.save(newBookEntity);

        String saveFolder = baseFolder + "books/images/" + savedBookEntity.getId() + "/";

        String imagePath = mediaService.saveFile(saveFolder, image);
        String pdfPath = mediaService.saveFile(saveFolder.replace("images", "pdfs"), pdf);

        savedBookEntity.setImageId(imagePath);
        savedBookEntity.setPdfId(pdfPath);
        repository.save(savedBookEntity);

        return "new message";

    }

    @Override
    public String update(BookUpdateDto bookUpdateDto) {
        BookEntity entity = toBookEntity(bookUpdateDto);
        repository.save(entity);
        return "the book has been updated successfully";
    }

    @Override
    public String delete(String id) {
        String message = id + " ID not found";

        Optional<BookEntity> entity = repository.findById(id);
        if (entity.isPresent()){
            BookEntity entity1 = entity.get();
            mediaService.deleteFile(entity1.getImageId());
            mediaService.deleteFile(entity1.getPdfId());
            message = id + " successfully deleted";
        }
        return message;
    }

    public static <T extends BookCreateDto> BookEntity toBookEntity(T dto){

        return new BookEntity(dto.getId(),
                dto.getBookName(),
                dto.getAuthorName(),
                dto.getDescription(),
                dto.getLanguage(),
                dto.getPublishedDate(),
                dto.getReleaseYear(),
                dto.getPages()
                );
    }

    public static BookCreateDto toBookDto(BookEntity bookEntity){
        if (bookEntity == null) {
            return null;
        }

        BookCreateDto bookCreateDto = new BookCreateDto();
        bookCreateDto.setBookName(bookEntity.getBookName());
        bookCreateDto.setAuthorName(bookEntity.getAuthorName());
        bookCreateDto.setDescription(bookEntity.getDescription());

        if (bookEntity.getCategory() != null) {
            bookCreateDto.setCategoryId(bookEntity.getCategory().getId());
        }

        if (bookEntity.getPublisher() != null) {
            bookCreateDto.setPublisher(bookEntity.getPublisher().getId());
        }

        bookCreateDto.setLanguage(bookEntity.getLanguage());
        bookCreateDto.setPublishedDate(bookEntity.getPublishedDate());
        bookCreateDto.setReleaseYear(bookEntity.getReleaseYear());
        bookCreateDto.setPages(bookEntity.getPages());

        return bookCreateDto;
    }

    public static BookListDto toBookListDto(BookEntity bookEntity){
        if (bookEntity == null) {
            return null;
        }

        BookListDto bookListDto = new BookListDto();
        bookListDto.setBookName(bookEntity.getBookName());
        bookListDto.setAuthorName(bookEntity.getAuthorName());
        bookListDto.setDescription(bookEntity.getDescription());

        if (bookEntity.getCategory() != null) {
            bookListDto.setCategoryName(bookEntity.getCategory().getCategoryName());
        }

        if (bookEntity.getPublisher() != null) {
            bookListDto.setPublisherName(bookEntity.getPublisher().getPublisherName());
        }

        bookListDto.setReleaseYear(bookEntity.getReleaseYear());
        bookListDto.setImageId(bookEntity.getImageId());

        return bookListDto;
    }
}
