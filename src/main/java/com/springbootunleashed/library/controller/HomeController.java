package com.springbootunleashed.library.controller;

import com.springbootunleashed.library.domain.Book;
import com.springbootunleashed.library.domain.BookEntity;
import com.springbootunleashed.library.domain.BookSearch;
import com.springbootunleashed.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
  private final BookService bookService;

  public HomeController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("books", bookService.getBooks());

    return "index";
  }

  @PostMapping("/add-book")
  public String newBook(@ModelAttribute Book book) {
    bookService.createBook(book);

    return "redirect:/";
  }

  @PostMapping("/find-book")
  public String bookSearch(@ModelAttribute BookSearch bookSearch,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      Model model) {
    Sort sort = Sort.by(Sort.Order.asc("title")); // default sort by title

    if (Boolean.TRUE.equals(bookSearch.sortByTitle().orElse(null))) {
      sort = Sort.by(Sort.Order.asc("title"));
    } else if (Boolean.TRUE.equals(bookSearch.sortByCategory().orElse(null))) {
      sort = Sort.by(Sort.Order.asc("category"));
    }
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<BookEntity> searchResults = bookService.findBooks(bookSearch, pageable);

    model.addAttribute("books", searchResults.getContent()); // Get content from the page
    model.addAttribute("totalPages", searchResults.getTotalPages());
    model.addAttribute("totalItems", searchResults.getTotalElements());
    model.addAttribute("currentPage", page);

    return "index";
  }


}
