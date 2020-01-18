package com.sebasira.MediumTutorialSecurity.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sebasira.MediumTutorialSecurity.model.Book;
import com.sebasira.MediumTutorialSecurity.repository.BookRepository;


/**
 * @author Chaturanand Yadav
 * @version 1.0.0
 * @since 2020-01-14
 */


@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookRepository repository;

	// list the Books
	@GetMapping(value = "/listbooks")
	List<Book> findall() {
		return repository.findAll();
	}

	// Save the Books
	@PostMapping("/savebook")
	@ResponseStatus(HttpStatus.CREATED)
	Book newBook(@Valid @RequestBody Book newBook) {
		return repository.save(newBook);
	}

	// Save or update
	@PutMapping("/books/{id}")
	Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {

		return repository.findById(id).map(x -> {
			x.setName(newBook.getName());
			x.setAuthor(newBook.getAuthor());
			x.setPrice(newBook.getPrice());
			return repository.save(x);
		}).orElseGet(() -> {
			newBook.setId(id);
			return repository.save(newBook);
		});
	}

	// delete the book
	@DeleteMapping("/deltebooks/{id}")
	void deleteBook(@PathVariable Long id) {
		repository.delete(id);
	}

}
