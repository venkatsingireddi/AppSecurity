package com.security.app.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.security.app.entity.Book;
/**
 * @author VenkatS
 *
 */
@Repository
@CrossOrigin(origins = "http://localhost:8080")
public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findByName(String username);

}
