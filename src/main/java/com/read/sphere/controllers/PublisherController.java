package com.read.sphere.controllers;

import com.read.sphere.dtos.create.PublisherCreateDto;
import com.read.sphere.services.PublisherService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @GetMapping("/add/form")
    public String addForm(Model theModel){
        PublisherCreateDto publisherCreateDto = new PublisherCreateDto();

        theModel.addAttribute("publisherDto", publisherCreateDto);

        return "publisher/add-publisher-form.html";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("publisherDto") PublisherCreateDto publisherCreateDto,
            @RequestParam("image")MultipartFile imageFile){

        service.create(publisherCreateDto);

        return "redirect:/books";
    }


}
