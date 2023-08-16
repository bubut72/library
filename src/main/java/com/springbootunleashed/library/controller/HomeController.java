package com.springbootunleashed.library.controller;

import com.springbootunleashed.library.domain.Book;
import com.springbootunleashed.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

}
