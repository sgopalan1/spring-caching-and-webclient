package com.srini.controller;

import com.srini.model.Quote;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CacheConfig(cacheNames = "books")
@RestController
public class ServerController {
    @GetMapping("/slow-service-tweets")
    public List<Quote> getAllQuotes() throws InterruptedException {
        Thread.sleep(2000L); // delay
        return List.of(
                new Quote("type1", "value1"),
                new Quote("type1", "@user2"),
                new Quote("type3", "@user1"));
    }

    @Cacheable
    @GetMapping("/cached-slow-service-tweets")
    public List<Quote> getAllQuotesCached() throws InterruptedException {
        Thread.sleep(2000L); // delay
        return List.of(
                new Quote("type1", "value1"),
                new Quote("type1", "@user2"),
                new Quote("type3", "@user1"));
    }
}
