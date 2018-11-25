package com.security.app.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.security.app.entity.Book;
@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface bookRepository extends JpaRepository<Book, Integer> {

	Book findByName(String username);

}
