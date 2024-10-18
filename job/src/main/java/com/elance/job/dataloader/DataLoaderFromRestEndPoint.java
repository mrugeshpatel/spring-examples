package com.elance.job.dataloader;

//import com.hubspot.billing.dto.CustomerRecord;
//import com.hubspot.billing.dto.CustomerRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Component
public class DataLoaderFromRestEndPoint {
/*
    Logger logger = LogManager.getLogger(DataLoaderFromRestEndPoint.class.getName());

    @Value("${hubspot.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    private List<CustomerRecord> customerRecordList = new ArrayList<>();

    public DataLoaderFromRestEndPoint(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        try {
            ResponseEntity<CustomerRecords> responseEntity =
                    restTemplate.exchange(
                            apiUrl + "?" + "userKey=4eb65a4299c20a3e48317480c781",
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<CustomerRecords>() {}
                    );
            CustomerRecords customerRecords = responseEntity.getBody();
            customerRecordList.addAll(customerRecords.getCallRecords());
        }catch (HttpClientErrorException e) {
            logger.error("Exception occurred while getting customers", e.getMessage());
        }
    }

    public List<CustomerRecord> getCustomerRecords() {
        return customerRecordList;
    }

 */
}
