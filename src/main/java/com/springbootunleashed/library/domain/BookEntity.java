package com.springbootunleashed.library.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_details")
@NoArgsConstructor
@Data
public class BookEntity {
  @Id
  @GeneratedValue
  private Long id;

  String title;

  String category;

  String isbn;

  public BookEntity(String title, String category, String isbn) {
    this.title = title;
    this.category = category;
    this.isbn = isbn;
  }
}
