package com.springbootunleashed.library.service;

import com.springbootunleashed.library.domain.Book;
import com.springbootunleashed.library.domain.BookEntity;
import com.springbootunleashed.library.domain.BookSearch;
import java.util.List;

public interface BookService {
  public List<BookEntity> getBooks();

  public BookEntity createBook(Book book);

  public List<BookEntity> findBooks(BookSearch bookSearch);
}
