package com.hubspot.billing.service;

import com.hubspot.billing.dto.BillingResponse;
import com.hubspot.billing.dto.BillingResult;
import com.hubspot.billing.dto.CustomerRecord;

import java.util.List;

public interface BillingService {

    BillingResult calculateBillAndPost();
}
