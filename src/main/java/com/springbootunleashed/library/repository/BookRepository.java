package com.springbootunleashed.library.repository;

import com.springbootunleashed.library.domain.BookEntity;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
  List<BookEntity> findByTitleContainsIgnoreCase(String partialTitle, Sort sortByTitle);

  List<BookEntity> findByCategoryContainsIgnoreCase(String partialCategory, Sort sortByCategory);

  List<BookEntity> findByTitleContainsOrCategoryContainsAllIgnoreCase(String partialTitle,
      String partialCategory, Sort sort);
}