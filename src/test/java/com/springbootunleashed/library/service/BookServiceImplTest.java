package com.springbootunleashed.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.springbootunleashed.library.domain.BookEntity;
import com.springbootunleashed.library.domain.BookSearch;
import com.springbootunleashed.library.repository.BookRepository;
import java.util.Collections;
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

    Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("title")));

    when(repository.findByTitleContainsIgnoreCase(any(), any())).thenReturn(new PageImpl<>(Collections.emptyList()));

    Page<BookEntity> resultPage = bookService.findBooks(search, pageable);

    assertEquals(Collections.emptyList(), resultPage.getContent());
  }

  @Test
  void testFindBooksByCategory() {
    BookSearch search = new BookSearch(null, "Fiction", Optional.empty(), Optional.of(true));

    Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("category")));

    when(repository.findByCategoryContainsIgnoreCase(any(), any())).thenReturn(new PageImpl<>(Collections.emptyList()));

    Page<BookEntity> resultPage = bookService.findBooks(search, pageable);

    assertEquals(Collections.emptyList(), resultPage.getContent());
  }

  @Test
  void testFindBooksByTitleAndCategory() {
    BookSearch search = new BookSearch("Sample Title", "Fiction", Optional.of(true), Optional.of(true));

    Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("title")));

    when(repository.findByTitleContainsOrCategoryContainsAllIgnoreCase(any(), any(), any())).thenReturn(new PageImpl<>(Collections.emptyList()));

    Page<BookEntity> resultPage = bookService.findBooks(search, pageable);

    assertEquals(Collections.emptyList(), resultPage.getContent());
  }

  // You can add more test cases if needed

}
