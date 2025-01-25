package com.read.sphere.controllers;

import com.read.sphere.dtos.create.BookCreateDto;
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


        if (books.hasContent()) {
            // Veriler mevcutsa içeriği yazdır
            System.out.println("Toplam kitap sayısı: " + books.getTotalElements());
            System.out.println("Toplam sayfa sayısı: " + books.getTotalPages());
            books.getContent().forEach(book -> System.out.println(book));
        } else {
            // Veri gelmediyse
            System.out.println("Veri bulunamadı.");
        }

        theModel.addAttribute("booksList", books);
        return "books/books-list.html";
    }

    @GetMapping("/form")
    public String bookCreateForm(Model theModel){

        BookCreateDto bookDto = new BookCreateDto();

        List<BookCategoryListDto> bookCategoryListDto = bookCategoryService.list();

        theModel.addAttribute("bookDto", bookDto);
        theModel.addAttribute("categories", bookCategoryListDto);

        return "books/book-create-form.html";
    }

    @PostMapping("/save")
    public String bookSave(
            @ModelAttribute("bookDto") BookCreateDto bookCreateDto,
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam("pdf") MultipartFile pdfFile
    ){

        System.out.println("SAVE");
        System.out.println(bookCreateDto);
        System.out.println("files");
        System.out.println(imageFile);
        System.out.println("pdf");
        System.out.println(pdfFile);
        System.out.println("SAVE");

        service.create(bookCreateDto, imageFile, pdfFile);
        return "redirect:/books";
    }
}


