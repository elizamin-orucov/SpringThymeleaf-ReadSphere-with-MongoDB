package com.read.sphere.controllers;

import com.read.sphere.models.BookEntity;
import com.read.sphere.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<BookEntity> books = service.getAllBooks(page, size);
        System.out.println("----------------------------");
        System.out.println(books);
        System.out.println("----------------------------");
        return "books/books-list.html";
    }

    @GetMapping("/form")
    public String bookCreateForm(Model theModel){

        BookEntity bookEntity = new BookEntity();

        theModel.addAttribute("bookEntity", bookEntity);

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


