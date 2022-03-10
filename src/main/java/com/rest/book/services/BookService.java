package com.rest.book.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rest.book.dao.BookRepository;
import com.rest.book.entity.Book;

@Component
public class BookService {

	public static List<Book> list = new ArrayList<>();
	
	@Autowired
	private BookRepository bookRepository;
	
	static {
		
		/*
		 * list.add(new Book(103,"The Partial Reference_1","Acd_1")); list.add(new
		 * Book(104,"The Partial Reference_2","Acd_2")); list.add(new
		 * Book(105,"The Partial Reference_3","Acd_3")); list.add(new
		 * Book(106,"The Partial Reference_4","Acd_4")); list.add(new
		 * Book(107,"The Partial Reference_5","Acd_5"));
		 */
		 
	}
	
	public List<Book> getAllBooks(){
		return (List<Book>) bookRepository.findAll();
	}
	
	public Book getBook(int id) {
		Book b=null;
		try {
		 b = list.stream().filter(e -> e.getId()== id ).findFirst().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookRepository.findById(id);
	}
	
	public boolean addBook(Book book) {
		 //return list.add(book);
		 Book save = bookRepository.save(book);
		 if(save != null)
			 return true;
		 return false;
	}
	
	public Book updateBook(Book book) {
		Iterator<Book> itr = list.iterator();
		Book b=null;
		while(itr.hasNext()) {
			b = itr.next();
			if(b.getId() == book.getId()) {
				b.setAuthor(book.getAuthor());
				b.setTitle(book.getTitle());
				break;
			}
		}
		
		return b;
	}
	
	public Book deleteBook(Book book) {
		/*
		 * Book b = list.stream().filter(e->e.getId() ==
		 * book.getId()).findFirst().get(); list.remove(b);
		 */
		
		/*
		 * list.stream().filter(e->e.getId() == book.getId()).findFirst().map(a->{
		 * list.remove(a); return a; });
		 */
		bookRepository.deleteById(book.getId());
		return book;
	}
}
