package com.springbootunleashed.library.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.springbootunleashed.library.domain.Book;
import com.springbootunleashed.library.domain.BookEntity;
import com.springbootunleashed.library.domain.BookSearch;
import com.springbootunleashed.library.service.BookService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

class HomeControllerTest {
  @Mock
  private BookService bookService;

  @InjectMocks
  private HomeController homeController;

  @Mock
  private Model model;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testIndex() {
    List<BookEntity> books = Collections.emptyList();
    when(bookService.getBooks()).thenReturn(books);

    String viewName = homeController.index(model);

    verify(model).addAttribute("books", books);
    assert viewName != null && viewName.equals("index");
  }

  @Test
  public void testBookSearch_SortByTitle() {
    BookSearch bookSearch = new BookSearch(null, null, Optional.of(true), Optional.empty());
    int page = 0;
    int size = 10;
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("title")));
    List<BookEntity> searchResults = Collections.emptyList();
    Page<BookEntity> pageResult = new PageImpl<>(searchResults);

    when(bookService.findBooks(eq(bookSearch), eq(pageable))).thenReturn(pageResult);

    String viewName = homeController.bookSearch(bookSearch, page, size, model);

    verify(model).addAttribute("books", searchResults);
    verify(model).addAttribute("totalPages", pageResult.getTotalPages());
    verify(model).addAttribute("totalItems", pageResult.getTotalElements());
    verify(model).addAttribute("currentPage", page);
    assert viewName != null && viewName.equals("index");
  }

  // Add similar tests for other scenarios, such as sorting by category.

  @Test
  public void testNewBook() {
    String viewName = homeController.newBook(new Book("Title", "Category", "ISBN"));

    verify(bookService).createBook(any(Book.class));
    assert viewName != null && viewName.equals("redirect:/");
  }
}
