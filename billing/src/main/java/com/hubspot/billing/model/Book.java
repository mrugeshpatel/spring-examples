package com.hubspot.billing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Book {

    @Id
    private Long id;
    private String title;
    private String description;
    private String publisher;
    private Double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;

}

