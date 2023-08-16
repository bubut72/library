package com.springbootunleashed.library.repository;

import com.springbootunleashed.library.domain.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
  Page<BookEntity> findByTitleContainsIgnoreCase(String partialTitle, Pageable pageable);

  Page<BookEntity> findByCategoryContainsIgnoreCase(String partialCategory, Pageable pageable);

  Page<BookEntity> findByTitleContainsOrCategoryContainsAllIgnoreCase(String partialTitle,
      String partialCategory, Pageable pageable);
}