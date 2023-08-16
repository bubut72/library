package com.springbootunleashed.library.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.springbootunleashed.library.domain.BookEntity;
import com.springbootunleashed.library.service.BookService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

class HomeControllerTest {

  @Mock
  private BookService bookService;

  @InjectMocks
  private HomeController homeController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testIndex() {
    Model model = mock(Model.class);
    List<BookEntity> books = Collections.emptyList();

    when(bookService.getBooks()).thenReturn(books);

    String viewName = homeController.index(model);

    verify(bookService).getBooks();
    verify(model).addAttribute(eq("books"), eq(books));

    assertEquals("index", viewName);
  }

  // Add more test methods for HomeController if needed

}
