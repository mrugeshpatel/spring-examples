package com.hubspot.billing.config;

import com.hubspot.billing.model.Book;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Comparator;
import java.util.PriorityQueue;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }

    @Bean
    public PriorityQueue<Book> expensiveBookHeap() {
        /*return new PriorityQueue<>((o1,o2) -> {
            return o1.getPrice().compareTo(o2.getPrice());
        }
        );*/
        //return new PriorityQueue<>(Comparator.comparing(Book::getPrice).reversed());
        return new PriorityQueue<>(Comparator.comparing(Book::getPrice));
    }

    @Bean
    public PriorityQueue<Book> recentlyPublishedBookHeap() {
        return new PriorityQueue<>(Comparator.comparing(Book::getPublishedDate).reversed());
    }
}
