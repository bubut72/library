package com.springbootunleashed.library.service;

import com.springbootunleashed.library.domain.Book;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
  private List<Book> books = List.of(new Book("The Great Gatsby", "Fiction", "978-0-7432-7356-5"),
      new Book("Introduction to Computer Science", "Non-Fiction", "978-0-13-469451-1"),
      new Book("Cooking Around the World", "Cooking", "978-1-250-12345-6"));

  @Override
  public List<Book> getBooks() {
    return books;
  }

  @Override
  public Book createBook(Book newBook) {
   List<Book> bookList = new ArrayList<>(books);
   bookList.add(newBook);
   this.books = List.copyOf(bookList);

   return newBook;
  }
}
