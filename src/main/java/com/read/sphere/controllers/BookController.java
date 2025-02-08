package com.read.sphere.controllers;

import com.read.sphere.dtos.create.BookCreateDto;
import com.read.sphere.dtos.detail.BookDetailDto;
import com.read.sphere.dtos.list.BookCategoryListDto;
import com.read.sphere.dtos.list.BookListDto;
import com.read.sphere.dtos.list.PublisherListDto;
import com.read.sphere.services.BookCategoryService;
import com.read.sphere.services.BookService;
import com.read.sphere.services.PublisherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService service;
    private final BookCategoryService bookCategoryService;
    private final PublisherService publisherService;

    public BookController(
            BookService service,
            PublisherService publisherService,
            BookCategoryService bookCategoryService
    ) {
        this.service = service;
        this.publisherService = publisherService;
        this.bookCategoryService = bookCategoryService;
    }

    @GetMapping
    public String list(
            Model theModel,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<BookListDto> books = service.list(pageable);

        theModel.addAttribute("booksList", books);
        return "books/books-list.html";
    }

    @GetMapping("/detail/{id}")
    public String detailBook(
            Model theModel,
            @PathVariable String id
    ){
        BookDetailDto detailDto = service.findById(id);

        theModel.addAttribute("detailDto", detailDto);

        return "books/book-detail.html";
    }

    @GetMapping("/form")
    public String bookCreateForm(Model theModel){

        BookCreateDto bookDto = new BookCreateDto();

        List<BookCategoryListDto> bookCategoryListDto = bookCategoryService.list();

        List<PublisherListDto> publisherListDto = publisherService.list();

        theModel.addAttribute("bookDto", bookDto);
        theModel.addAttribute("categories", bookCategoryListDto);
        theModel.addAttribute("publishers", publisherListDto);

        return "books/book-create-form.html";
    }

    @PostMapping("/save")
    public String bookSave(
            @ModelAttribute("bookDto") BookCreateDto bookCreateDto,
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam("pdf") MultipartFile pdfFile
    ){
        service.create(bookCreateDto, imageFile, pdfFile);
        return "redirect:/books";
    }

    @PostMapping("/delete")
    public String bookDelete(
            @RequestParam("id") String bookId
    ){
        System.out.println("delete gelen sorgu:   ------  ");
        System.out.println("silinen id " + bookId);
        service.delete(bookId);
        return "redirect:/books";
    }
}


