package com.hubspot.billing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRecord {

    private Long customerId;
    private String callId;
    private Long startTimestamp;
    private Long endTimestamp;
}
