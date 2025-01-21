package com.edu.bookstatistics.controllers;

import com.edu.bookstatistics.dto.BookDTO;
import com.edu.bookstatistics.entities.Book;
import com.edu.bookstatistics.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    @Operation(summary = "Add a new book", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setTotalPages(bookDTO.getTotalPages());
        book.setCoverImage(bookDTO.getCoverImage());
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    @Operation(summary = "Get all books", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}