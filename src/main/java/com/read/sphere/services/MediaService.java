package com.read.sphere.services;

import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    byte[] readFile(String path);

    byte[] getBookFile(String id, String fileType);

    String saveFile(String folderNameForeSave, MultipartFile file);

    boolean deleteFile(String filePath);

    byte[] getAuthorPhoto(String id);

}
