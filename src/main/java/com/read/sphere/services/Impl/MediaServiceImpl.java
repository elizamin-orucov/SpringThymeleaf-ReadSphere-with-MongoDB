package com.read.sphere.services.Impl;

import com.read.sphere.services.MediaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Service
public class MediaServiceImpl implements MediaService {

    @Value("${baseFolder}")
    private String baseFolder;

    private final String uploadDir = baseFolder + LocalDate.now() + "/";

    @Override
    public byte[] getFile(String path) {
        try {
            if (path != null) {
                return Files.readAllBytes(Paths.get(path));
            }
            else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String saveFile(String folderNameForeSave, MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();

                Path path = Paths.get(folderNameForeSave + "/" + file.getOriginalFilename());
                Files.createDirectories(path.getParent());

                Files.write(path, bytes);
                return path.toString();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.deleteIfExists(path);
        } catch (IOException e) {
            // e.printStackTrace();
            return false;
        }
    }
}
