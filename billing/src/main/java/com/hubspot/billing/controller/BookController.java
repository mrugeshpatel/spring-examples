package com.hubspot.billing.controller;

import com.hubspot.billing.model.Book;
import com.hubspot.billing.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class BookController {

    Logger logger = LogManager.getLogger(BookController.class.getName());

    @Autowired
    private BookService bookService;

    @GetMapping("book/topexpensivebooks")
    public ResponseEntity<List<Book>> topExpensiveBooks(
            @RequestParam(value = "noofbooks", defaultValue = "10", required = false) int noOfBooks){
        return ResponseEntity.ok().body(bookService.topExpensiveBook(noOfBooks));
    }

    @GetMapping("book/recentlypublishedbooks")
    public ResponseEntity<List<Book>> recentlyPublishedBook(
            @RequestParam(value = "noofbooks", defaultValue = "10", required = false) int noOfBooks){
        return ResponseEntity.ok().body(bookService.recentPublishedBook(noOfBooks));
    }

}
