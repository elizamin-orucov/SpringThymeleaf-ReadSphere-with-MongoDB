package com.read.sphere.services.Impl;

import com.read.sphere.models.BookEntity;
import com.read.sphere.repositories.BookRepository;
import com.read.sphere.services.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
public class MediaServiceImpl implements MediaService {

    @Value("${imageType}")
    private String imageType;

    @Value("${pdfType}")
    private String pdfType;

    @Value("${baseFolder}")
    private String baseFolder;

    private final BookRepository bookRepository;

    private final String uploadDir = baseFolder + LocalDate.now() + "/";

    public MediaServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public byte[] readFile(String path) {
        try {
            Path paths = Paths.get(path);
            if (Files.exists(paths)) {
                log.info("Successfully read file: {}", path);
                return Files.readAllBytes(paths);
            }
            else {
                log.warn("File not found: {}", path);
                return new byte[0];
            }
        } catch (IOException | NullPointerException e) {
            log.error("Error reading file: {}", path, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getFile(String id, String fileType) {
        Optional<BookEntity> optionalEntity = bookRepository.findById(id);

        if (optionalEntity.isPresent()) {
            BookEntity entity = optionalEntity.get();

            if (fileType.equals(imageType)) {
                log.info("Retrieving image for book ID: {}", id);
                return readFile(entity.getImageId());
            }
            else if (fileType.equals(pdfType)){
                log.info("Retrieving PDF for book ID: {}", id);
                return readFile(entity.getPdfId());
            }
        }
        log.warn("No file found for book ID: {} and type: {}", id, fileType);
        return new byte[0];
    }

    @Override
    public String saveFile(String folderNameForeSave, MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();

                Path path = Paths.get(folderNameForeSave + "/" + file.getOriginalFilename());
                Files.createDirectories(path.getParent());

                Files.write(path, bytes);
                log.info("File saved successfully: {}", path);
                return path.toString();
            }
            log.warn("Attempted to save empty file");

        } catch (IOException e) {
            log.error("Error saving file", e);
        }
        return null;
    }

    @Override
    public boolean deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            boolean deleted = Files.deleteIfExists(path);

            if (deleted) {
                log.info("File deleted successfully: {}", filePath);
            } else {
                log.warn("File not found for deletion: {}", filePath);
            }
            return deleted;

        } catch (IOException e) {
            log.error("Error deleting file: {}", filePath, e);
            return false;
        }
    }
}
