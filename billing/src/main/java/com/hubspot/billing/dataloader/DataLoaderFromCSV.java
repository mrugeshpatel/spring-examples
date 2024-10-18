package com.hubspot.billing.dataloader;

import com.hubspot.billing.model.Book;
import com.hubspot.billing.repository.BookRepository;
import com.opencsv.CSVReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Component
public class DataLoaderFromCSV implements CommandLineRunner {

    Logger logger = LogManager.getLogger(DataLoaderFromCSV.class.getName());

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PriorityQueue<Book> expensiveBookHeap;

    @Autowired
    PriorityQueue<Book> recentlyPublishedBookHeap;

    @Override
    public void run(String... args) throws Exception {
        try {
            InputStream csvFile = getClass().getResourceAsStream("/book.csv");
            if (csvFile != null) {
                CSVReader csvReader = new CSVReader(new InputStreamReader(csvFile));
                String[] line;
                List<Book> books = new ArrayList<>();
                csvReader.readNext();
                while ((line = csvReader.readNext()) != null) {
                    Book book = Book.builder()
                            .id(Long.valueOf(line[0])).title(line[1])
                            .description(line[2]).publisher(line[3])
                            .price(Double.valueOf(line[4]))
                            .publishedDate(LocalDate.parse(line[5])).build();
                    books.add(book);
                    expensiveBookHeap.add(book);
                    recentlyPublishedBookHeap.add(book);
                }
                bookRepository.saveAll(books);
            }
        } catch (Exception e) {
            logger.error("Error occurred while reading csv file", e);
            throw e;
        }
    }
}
