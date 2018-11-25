package com.security.app.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.app.entity.Book;
import com.security.app.service.UserDetailsServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author VenkatS
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@Api(value="Books Controller",description="Books Controller",tags="Book Controller")
public class BookResource {
	
	public static final Logger log=LoggerFactory.getLogger(BookResource.class);
	
	@Autowired
	UserDetailsServiceImpl service;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@PostMapping("/books")
	@ResponseBody
	@Secured("ROLE_ADMIN")
	@ApiOperation("store the book Info")
	public String postBook(@RequestBody Book book) {
		
		String encPassword=encoder.encode(book.getPassword());
		book.setPassword(encPassword);
		
		service.insert(book);
		return "I am inserted";
		
	}
	
	@GetMapping("/books")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@ApiOperation("Get the List of All the Books")
	@ApiResponses(
			value= {
					@ApiResponse(code=200,message="You have successfully fetched the data"),
					@ApiResponse(code=404,message="Books are not Found")
			})
	public List<Book> getBook(){
		return service.getBook();
	}
	
	@GetMapping("/books/{id}")
	@ResponseBody
	@ApiOperation("Get the product with specific id")
	public Optional<Book> getSpecificBook(@PathVariable int id) {
		return service.getSpecificBook(id);
	}
	
	@DeleteMapping("/books/{id}")
	@ResponseBody
	@ApiOperation("Delete the book with the help of Specific Id")
	public void delBook(@PathVariable int id) {
		service.delBook(id);
		log.info("Successfully deleted");
		
	}
	@PutMapping("/books")
	@ResponseBody
	@ApiOperation("Update the Books Info")
	public void upBook(@RequestBody Book book) {
		
		String encPassword=encoder.encode(book.getPassword());
		book.setPassword(encPassword);
		
		service.upBook(book);
		log.info("Successfully updated");
		
	}

}
