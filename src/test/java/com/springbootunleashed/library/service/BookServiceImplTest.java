package com.springbootunleashed.library.service;

import com.springbootunleashed.library.domain.BookEntity;
import com.springbootunleashed.library.domain.BookSearch;
import com.springbootunleashed.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

  @Mock
  private BookRepository repository;

  @InjectMocks
  private BookServiceImpl bookService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindBooksByTitle() {
    BookSearch search = new BookSearch("Sample Title", null, Optional.of(true), Optional.empty());

    when(repository.findByTitleContainsIgnoreCase(any(), any())).thenReturn(Collections.emptyList());

    List<BookEntity> result = bookService.findBooks(search);

    verify(repository).findByTitleContainsIgnoreCase(eq("Sample Title"), any());

    assertEquals(Collections.emptyList(), result);
  }

  @Test
  void testFindBooksByCategory() {
    BookSearch search = new BookSearch(null, "Fiction", Optional.empty(), Optional.of(true));

    when(repository.findByCategoryContainsIgnoreCase(any(), any())).thenReturn(Collections.emptyList());

    List<BookEntity> result = bookService.findBooks(search);

    verify(repository).findByCategoryContainsIgnoreCase(eq("Fiction"), any());

    assertEquals(Collections.emptyList(), result);
  }

  @Test
  void testFindBooksByTitleAndCategory() {
    BookSearch search = new BookSearch("Sample Title", "Fiction", Optional.of(true), Optional.of(true));

    when(repository.findByTitleContainsOrCategoryContainsAllIgnoreCase(any(), any(), any())).thenReturn(Collections.emptyList());

    List<BookEntity> result = bookService.findBooks(search);

    verify(repository).findByTitleContainsOrCategoryContainsAllIgnoreCase(eq("Sample Title"), eq("Fiction"), any());

    assertEquals(Collections.emptyList(), result);
  }

}
