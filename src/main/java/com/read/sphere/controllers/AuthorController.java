package com.read.sphere.controllers;

import com.read.sphere.dtos.create.AuthorCreateDto;
import com.read.sphere.dtos.detail.AuthorDetailDto;
import com.read.sphere.dtos.list.AuthorListDto;
import com.read.sphere.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String list(
            Model theModel,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AuthorListDto> authors = authorService.list(pageable);

        theModel.addAttribute("authors", authors);
        return "authors/list.html";
    }

    @GetMapping("/{id}")
    public String detail(
            Model theModel,
            @PathVariable String id
    ){

        AuthorDetailDto detailDto = authorService.findById(id);

        theModel.addAttribute("author", detailDto);

        return "authors/detail.html";
    }

    @GetMapping("/add/form")
    public String addForm(Model theModel){

        AuthorCreateDto createDto = new AuthorCreateDto();

        theModel.addAttribute("authorDto", createDto);

        return "authors/add-authors-form.html";
    }


    @PostMapping("/save")
    public String save(
            @ModelAttribute("authorDto") AuthorCreateDto createDto,
            @RequestParam("image")MultipartFile image
            ){
        authorService.create(createDto, image);
        return "redirect:/authors";
    }

}
