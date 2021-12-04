package com.nanos.msscbreweryclient.web.client;

import com.nanos.msscbreweryclient.web.model.BeerDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "cn.brewery",ignoreInvalidFields = false)
@Data
public class BreweryClient {
    private String apiHost;
    public RestTemplate restTemplate;
    public static final String BREWERY_PATH="api/v1/beer/";
    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    public BeerDto getBeerById(UUID uuid){
        return restTemplate.getForObject(apiHost+BREWERY_PATH+uuid,BeerDto.class);
    }
    public URI saveNewBeer(BeerDto beerDto){
        return restTemplate.postForLocation(apiHost+BREWERY_PATH,beerDto);
    }
    public void updateBeer(UUID uuid,BeerDto beerDto){
        restTemplate.put(apiHost+BREWERY_PATH+uuid,beerDto);
    }
    public void deleteBeer(UUID uuid){
        restTemplate.delete(apiHost+BREWERY_PATH+uuid);
    }
}
