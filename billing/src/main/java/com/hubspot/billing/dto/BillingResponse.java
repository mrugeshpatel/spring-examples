package com.hubspot.billing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillingResponse {
    private Long customerId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;
    private Integer maxConcurrentCalls;
    private Long timestamp;
    private Set<String> callIds;
}
