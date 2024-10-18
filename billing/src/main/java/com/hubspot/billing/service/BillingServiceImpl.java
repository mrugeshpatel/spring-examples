package com.hubspot.billing.service;

import com.hubspot.billing.dataloader.DataLoaderFromCSV;
import com.hubspot.billing.dataloader.DataLoaderFromRestEndPoint;
import com.hubspot.billing.dto.BillingResponse;
import com.hubspot.billing.dto.BillingResult;
import com.hubspot.billing.dto.CustomerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService{

    Logger logger = LogManager.getLogger(BillingServiceImpl.class.getName());

    @Value("${hubspot.submit.url}")
    private String submitUrl;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private final RestTemplate restTemplate;
    private final DataLoaderFromRestEndPoint dataLoader;

    public BillingServiceImpl(RestTemplate restTemplate, DataLoaderFromRestEndPoint dataLoader) {
        this.restTemplate = restTemplate;
        this.dataLoader = dataLoader;
    }
    @Override
    public BillingResult calculateBillAndPost() {
        Set<BillingResponse> result = new HashSet<>();
        List<CustomerRecord> customerRecords = dataLoader.getCustomerRecords();

        Map<Long, List<CustomerRecord>> groupedCustomers = customerRecords.stream()
                .collect(Collectors.groupingBy(CustomerRecord::getCustomerId));

        for(Map.Entry<Long, List<CustomerRecord>> entry : groupedCustomers.entrySet()) {

            List<CustomerRecord> customerRecordList = entry.getValue();
            for(int i=0; i<customerRecordList.size(); i++) {
                CustomerRecord customerRecord = customerRecordList.get(i);
                BillingResponse billingResponse = new BillingResponse();
                billingResponse.setCustomerId(customerRecord.getCustomerId());
                Date date = new Date(customerRecord.getStartTimestamp());
                format.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
                String formatted = format.format(date);
                billingResponse.setDate(formatted);
                billingResponse.setTimestamp(customerRecord.getStartTimestamp());
                int match=1;
                Set<String> callIds = new HashSet<>();
                callIds.add(customerRecord.getCallId());
                for(int j=0; j<customerRecordList.size(); j++) {
                    if(i!=j) {
                        CustomerRecord customerRecord2 = customerRecordList.get(j);
                        if(customerRecord.getStartTimestamp() < customerRecord2.getStartTimestamp()) {
                            continue;
                        }
                        if(hasOverlap(customerRecord.getStartTimestamp(), customerRecord.getEndTimestamp(),
                                customerRecord2.getStartTimestamp(), customerRecord2.getEndTimestamp())) {
                            match++;
                            callIds.add(customerRecord2.getCallId());
                        }
                    }
                }
                billingResponse.setMaxConcurrentCalls(match);
                billingResponse.setCallIds(callIds);
                System.out.println(billingResponse);
                result.add(billingResponse);
            }
        }
        return post(result);
    }

    private BillingResult post(Set<BillingResponse> res) {

        BillingResult result = new BillingResult();
        result.setResults(res);
        return restTemplate.postForObject(submitUrl, result, BillingResult.class);


    }

    private boolean hasOverlap(long start1, long end1, long start2, long end2) {
        return start1 <= end2 && start2 <= end1;
    }
}
