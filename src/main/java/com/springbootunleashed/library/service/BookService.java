package com.springbootunleashed.library.service;

import com.springbootunleashed.library.domain.Book;
import java.util.List;

public interface BookService {
  public List<Book> getBooks();

  public Book createBook(Book book);
}
