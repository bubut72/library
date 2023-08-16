package com.springbootunleashed.library.controller;

import com.springbootunleashed.library.domain.Book;
import com.springbootunleashed.library.domain.BookEntity;
import com.springbootunleashed.library.service.BookService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
  private final BookService bookService;

  public ApiController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/api/books")
  public List<BookEntity> all(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);

    return bookService.getBooks(pageable).getContent();
  }

  @PostMapping("/api/books")
  public ResponseEntity<BookEntity> newBook(@RequestBody Book newBook) {
    BookEntity createdBook = bookService.createBook(newBook);

    if (createdBook != null) {
      return ResponseEntity.ok(createdBook); // Return 200 OK with the created book entity
    } else {
      return ResponseEntity.badRequest().build(); // Return 400 Bad Request if creation fails
    }
  }
}
