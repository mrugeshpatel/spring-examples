package com.hubspot.billing.controller;

import com.hubspot.billing.dataloader.DataLoaderFromCSV;
import com.hubspot.billing.dto.BillingResult;
import com.hubspot.billing.service.BillingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class BillingController {

    Logger logger = LogManager.getLogger(DataLoaderFromCSV.class.getName());

    @Autowired
    BillingService billingService;

    @GetMapping("billing")
    public ResponseEntity<BillingResult> calculateBill() {
        billingService.calculateBillAndPost();
        return ResponseEntity.ok().body(billingService.calculateBillAndPost());
    }



}
