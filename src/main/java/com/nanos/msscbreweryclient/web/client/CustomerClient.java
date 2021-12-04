package com.nanos.msscbreweryclient.web.client;

import com.nanos.msscbreweryclient.web.model.CustomerDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@Data
@ConfigurationProperties(value = "cn.brewery",ignoreInvalidFields = false)
public class CustomerClient {

    private String apiHost;
    private static final String CUSTOMER_PATH="api/v1/customer/";
    private RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    public CustomerDto getCustomerById(UUID uuid){
        return restTemplate.getForObject(apiHost+CUSTOMER_PATH+uuid,CustomerDto.class);
    }
    public URI saveNewCustomer(CustomerDto customerDto){
        return restTemplate.postForLocation(apiHost+CUSTOMER_PATH,customerDto);
    }
    public void updateCustomer(UUID uuid,CustomerDto customerDto){
        restTemplate.put(apiHost+CUSTOMER_PATH+uuid,customerDto);
    }
    public void deleteCustomer(UUID uuid){
        restTemplate.delete(apiHost+CUSTOMER_PATH+uuid);
    }
}
