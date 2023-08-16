package com.springbootunleashed.library.service;

import com.springbootunleashed.library.domain.Book;
import com.springbootunleashed.library.domain.BookEntity;
import com.springbootunleashed.library.domain.BookSearch;
import com.springbootunleashed.library.repository.BookRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BookServiceImpl implements BookService {
  private final BookRepository repository;

  public BookServiceImpl(BookRepository repository) {
    this.repository = repository;
  }

  @Override
  public Page<BookEntity> getBooks(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public BookEntity createBook(Book newBook) {
    return repository.saveAndFlush(new BookEntity(newBook.title(), newBook.category(),
        newBook.ISBN()));
  }

  @Override
  public Page<BookEntity> findBooks(BookSearch bookSearch, Pageable pageable) {
    Page<BookEntity> resultPage;

    if (StringUtils.hasText(bookSearch.title()) && StringUtils.hasText(bookSearch.category())) {
      resultPage = repository.findByTitleContainsOrCategoryContainsAllIgnoreCase(
          bookSearch.title(), bookSearch.category(), pageable);
    } else if (StringUtils.hasText(bookSearch.title())) {
      resultPage = repository.findByTitleContainsIgnoreCase(bookSearch.title(), pageable);
    } else if (StringUtils.hasText(bookSearch.category())) {
      resultPage = repository.findByCategoryContainsIgnoreCase(bookSearch.category(), pageable);
    } else {
      return new PageImpl<>(Collections.emptyList(), pageable, 0); // Return an empty page
    }

    return resultPage;
  }
}
