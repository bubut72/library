package com.springbootunleashed.library.controller;

import com.springbootunleashed.library.domain.Book;
import com.springbootunleashed.library.domain.BookEntity;
import com.springbootunleashed.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ApiControllerTest {

  @Mock
  private BookService bookService;

  @InjectMocks
  private ApiController apiController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAllBooks() {
    List<BookEntity> books = Collections.emptyList();

    when(bookService.getBooks()).thenReturn(books);

    List<BookEntity> result = apiController.all();

    verify(bookService).getBooks();
    assertEquals(books, result);
  }

  @Test
  void testCreateBook() {
    Book newBook = new Book("New Title", "Fiction", "1234567890");
    BookEntity createdBook = new BookEntity(newBook.title(), newBook.category(), newBook.ISBN());

    when(bookService.createBook(any(Book.class))).thenReturn(createdBook);

    ResponseEntity<BookEntity> responseEntity = apiController.newBook(newBook);

    verify(bookService).createBook(eq(newBook));
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(createdBook, responseEntity.getBody());
  }

  // Add more test methods for ApiController if needed

}
