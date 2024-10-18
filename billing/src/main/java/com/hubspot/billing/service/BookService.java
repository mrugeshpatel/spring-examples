package com.hubspot.billing.service;

import com.hubspot.billing.model.Book;

import java.util.List;

public interface BookService {
    List<Book> topExpensiveBook(int topNoOfBook);
    List<Book> recentPublishedBook(int topNoOfBook);
}
