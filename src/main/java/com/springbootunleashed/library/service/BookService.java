package com.springbootunleashed.library.service;

import com.springbootunleashed.library.domain.Book;
import com.springbootunleashed.library.domain.BookEntity;
import com.springbootunleashed.library.domain.BookSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
  public List<BookEntity> getBooks();

  public BookEntity createBook(Book book);

  public Page<BookEntity> findBooks(BookSearch bookSearch, Pageable pageable);
}
