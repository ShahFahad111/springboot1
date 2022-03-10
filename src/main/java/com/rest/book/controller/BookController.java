package com.rest.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.book.entity.Book;
import com.rest.book.services.BookService;


@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/book", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Book>> getAllBook() {	
		List<Book> allBooks = bookService.getAllBooks();
		
		if(allBooks.size() <=0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.of(Optional.of(allBooks));
	}
	
	@RequestMapping(value="/book/{id}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id){
		Book book =bookService.getBook(id); 
		
		if(book==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.of(Optional.of(book));
	}
	
	@RequestMapping(value="/book", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addBook(@RequestBody Book book) {
		String str;
		if(bookService.addBook(book))
			str= "Book Added";
		else
			str= "Error Adding Book";
		
		return  new ResponseEntity<>(str,HttpStatus.OK);
	}
	
	@RequestMapping(value="/book",method=RequestMethod.PUT)
	@ResponseBody
	public Book updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}
	
	@RequestMapping(value="/book", method=RequestMethod.DELETE)
	@ResponseBody
	public Book deleteBook(@RequestBody Book book) {
		return bookService.deleteBook(book);
	}
}
