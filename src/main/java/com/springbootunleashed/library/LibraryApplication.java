package com.springbootunleashed.library;

import com.springbootunleashed.library.domain.BookEntity;
import com.springbootunleashed.library.repository.BookRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {
	private final BookRepository bookRepository;

	public LibraryApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BookEntity book1 = new BookEntity("The Great Gatsby", "Fiction", "978-0-7432-7356-5");
		BookEntity book2 = new BookEntity("Introduction to Computer Science", "Non-Fiction", "978-0-13-469451-1");
		BookEntity book3 = new BookEntity("Cooking Around the World", "Cooking", "978-1-250-12345-6");

		List<BookEntity> books = List.of(book1, book2, book3);

		bookRepository.saveAll(books);
	}
}
