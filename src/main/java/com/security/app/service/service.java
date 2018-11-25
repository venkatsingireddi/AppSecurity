package com.security.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.app.entity.Book;

@Service
public class service implements UserDetailsService{
	
	public static final Logger log=LoggerFactory.getLogger(service.class);
	
	@Autowired
	bookRepository bookRepo;
	
	public int insert(Book book) {
		log.info("service " +book.getName());
		bookRepo.save(book);
		return book.getId();
	}
	
	public List<Book> getBook() {
		List<Book> books=new ArrayList<>();
		bookRepo.findAll().forEach(books::add);
		return books;
	}

	public Optional<Book> getSpecificBook(int id) {
		
		return bookRepo.findById(id);
	}
	
	public void delBook(int id) {
		bookRepo.deleteById(id);
		log.info("Deleted");
	}

	public void upBook(Book book) {
		bookRepo.save(book);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Book book=bookRepo.findByName(username);
		if(book==null) {
			throw new UsernameNotFoundException("User not Found");
		}
			return new bookDetailsImpl(book);
	}
}
