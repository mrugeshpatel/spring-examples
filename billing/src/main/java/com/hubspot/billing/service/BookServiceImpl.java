package com.hubspot.billing.service;

import com.hubspot.billing.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    PriorityQueue<Book> expensiveBookHeap;

    @Autowired
    PriorityQueue<Book> recentlyPublishedBookHeap;


    @Override
    public List<Book> topExpensiveBook(int topNoOfBook) {
        List<Book> result = new ArrayList<>();
        if(expensiveBookHeap == null)
            return result;
        int i=0;
        while(expensiveBookHeap.peek() != null && i < topNoOfBook) {
            i++;
            result.add(expensiveBookHeap.poll());
        }
        expensiveBookHeap.addAll(new ArrayList<Book>(result));
        return result;
    }

    @Override
    public List<Book> recentPublishedBook(int topNoOfBook) {
        List<Book> result = new ArrayList<>();
        if(recentlyPublishedBookHeap == null)
            return result;
        int i=0;
        while(recentlyPublishedBookHeap.peek() != null && i < topNoOfBook) {
            i++;
            result.add(recentlyPublishedBookHeap.poll());
        }
        recentlyPublishedBookHeap.addAll(new ArrayList<Book>(result));
        return result;
    }
}
