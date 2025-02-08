package com.read.sphere.controllers;

import com.read.sphere.services.MediaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media")
public class MediaController {
    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getBookImage(@PathVariable String id) {
        byte[] imageBytes = mediaService.getBookFile(id, "img");
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }

    @GetMapping("/author/profile/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getAuthorProfilePhoto(@PathVariable String id){
        byte[] imageByte = mediaService.getAuthorPhoto(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageByte);
    }
}
