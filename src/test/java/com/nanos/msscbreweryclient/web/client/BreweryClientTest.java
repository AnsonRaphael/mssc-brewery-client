package com.nanos.msscbreweryclient.web.client;

import com.nanos.msscbreweryclient.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;
    @Test
    void getBeerById() {
        BeerDto beerDto = breweryClient.getBeerById(UUID.randomUUID());
        assertNotNull(beerDto);
    }

    @Test
    void saveNewBeer() {
        BeerDto beerDto = BeerDto.builder().beerName("no idea")
                .beerId(UUID.randomUUID()).build();
        URI uri = breweryClient.saveNewBeer(beerDto);
        assertNotNull(uri);
       log.info(uri.toString());
    }

    @Test
    void updateBeer() {
        BeerDto beerDto = BeerDto.builder().beerName("New Update beer")
                .build();
        breweryClient.updateBeer(UUID.randomUUID(),beerDto);

    }

    @Test
    void deleteBeer() {
        breweryClient.deleteBeer(UUID.randomUUID());
    }
}