package com.rest.book.dao;

import org.springframework.data.repository.CrudRepository;

import com.rest.book.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	public Book findById(int id);
}
