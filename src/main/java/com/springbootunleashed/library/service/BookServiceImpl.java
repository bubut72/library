package com.springbootunleashed.library.service;

import com.springbootunleashed.library.domain.Book;
import com.springbootunleashed.library.domain.BookEntity;
import com.springbootunleashed.library.domain.BookSearch;
import com.springbootunleashed.library.repository.BookRepository;
import java.util.Collections;
import java.util.List;
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
  public List<BookEntity> getBooks() {
    return repository.findAll();
  }

  @Override
  public BookEntity createBook(Book newBook) {
    return repository.saveAndFlush(new BookEntity(newBook.title(), newBook.category(),
        newBook.ISBN()));
  }

  @Override
  public List<BookEntity> findBooks(BookSearch bookSearch) {
    Sort sort = Sort.by(Sort.Order.asc("title")); // default sort by title

    if (Boolean.TRUE.equals(bookSearch.sortByTitle().orElse(null)))
      sort = Sort.by(Sort.Order.asc("title"));
    else if (Boolean.TRUE.equals(bookSearch.sortByCategory().orElse(null)))
      sort =  Sort.by(Sort.Order.asc("category"));


    if (StringUtils.hasText(bookSearch.title())
        && StringUtils.hasText(bookSearch.category())) {
      return repository.findByTitleContainsOrCategoryContainsAllIgnoreCase(
          bookSearch.title(), bookSearch.category(), sort);
    }

    if (StringUtils.hasText(bookSearch.title())) {
      return repository.findByTitleContainsIgnoreCase(bookSearch.title(), sort);
    }

    if (StringUtils.hasText(bookSearch.category())) {
      return repository.findByCategoryContainsIgnoreCase(bookSearch.category(), sort);
    }

    return Collections.emptyList();
  }
}
