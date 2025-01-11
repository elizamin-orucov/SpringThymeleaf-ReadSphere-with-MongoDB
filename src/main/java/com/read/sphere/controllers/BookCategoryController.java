package com.read.sphere.controllers;

import com.read.sphere.dtos.create.BookCategoryCreateDto;
import com.read.sphere.services.BookCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/categories")
public class BookCategoryController {
    private final BookCategoryService service;

    public BookCategoryController(BookCategoryService service) {
        this.service = service;
    }

    @GetMapping("/form")
    public String bookCategoryCreateForm(Model theModel){

        BookCategoryCreateDto dto = new BookCategoryCreateDto();

        theModel.addAttribute("categoryDto", dto);

        return "categories/category-create-form.html";
    }

    @PostMapping("/save")
    public String categorySave(
            @ModelAttribute("categoryDto") BookCategoryCreateDto dto
    ){
        String message = service.create(dto);
        log.info(message);
        return "redirect:/books";
    }

}
