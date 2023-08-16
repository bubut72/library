package com.springbootunleashed.library.controller;

import com.springbootunleashed.library.domain.Book;
import com.springbootunleashed.library.service.BookService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
  private final BookService bookService;

  public ApiController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/api/books")
  public List<Book> all() {
    return bookService.getBooks();
  }
}
