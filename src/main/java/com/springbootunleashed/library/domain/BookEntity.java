package com.springbootunleashed.library.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
