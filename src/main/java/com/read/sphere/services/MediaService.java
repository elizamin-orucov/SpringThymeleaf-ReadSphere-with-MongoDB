package com.read.sphere.services;

import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    byte[] getFile(String path);

    String saveFile(String folderNameForeSave, MultipartFile file);

    boolean deleteFile(String filePath);

}
