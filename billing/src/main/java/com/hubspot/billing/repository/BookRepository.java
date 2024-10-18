package com.hubspot.billing.repository;

import com.hubspot.billing.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {

}
