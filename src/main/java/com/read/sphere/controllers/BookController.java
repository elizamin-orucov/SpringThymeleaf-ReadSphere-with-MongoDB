package com.read.sphere.controllers;

import com.read.sphere.dtos.list.BookCategoryListDto;
import com.read.sphere.dtos.list.BookListDto;
import com.read.sphere.models.BookEntity;
import com.read.sphere.services.BookCategoryService;
import com.read.sphere.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService service;
    private BookCategoryService bookCategoryService;

//    @GetMapping("/image/{id}")
//    @ResponseBody
//    public ResponseEntity<byte[]> getBookImage(@PathVariable String id) {
//        byte[] imageBytes = service.getBookImageById(id);
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_JPEG)
//                .body(imageBytes);
//    }

    public BookController(
            BookService service,
            BookCategoryService bookCategoryService
    ) {
        this.service = service;
        this.bookCategoryService = bookCategoryService;
    }

    @GetMapping
    public String list(
            Model theModel,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<BookListDto> books = service.list(pageable);
        System.out.println("----------------------------");
        System.out.println(books);
        System.out.println("----------------------------");
        return "books/books-list.html";
    }

    @GetMapping("/form")
    public String bookCreateForm(Model theModel){

        BookEntity bookEntity = new BookEntity();

        List<BookCategoryListDto> bookCategoryListDto = bookCategoryService.list();

        System.out.println("---------list--category");
        System.out.println(bookCategoryListDto);
        System.out.println("---------list--category");

        theModel.addAttribute("bookEntity", bookEntity);
        theModel.addAttribute("categories", bookCategoryListDto);

        return "books/book-create-form.html";
    }

    @PostMapping("/save")
    public String bookSave(
            @ModelAttribute("bookEntity") BookEntity bookEntity,
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam("pdf") MultipartFile pdfFile
    ){

        System.out.println("SAVE");
        System.out.println(bookEntity);
        System.out.println("files");
        System.out.println(imageFile);
        System.out.println("pdf");
        System.out.println(pdfFile);
        System.out.println("SAVE");
        return "redirect:/books";
    }
}


