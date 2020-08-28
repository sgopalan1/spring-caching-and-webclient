package com.srini.controller;

import com.srini.model.Quote;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class ClientController {
    private static final String URI = "http://localhost:8080/slow-service-quotes";

    @GetMapping("/quotes-blocking")
    public List<Quote> getquotesBlocking() {
        System.out.println("Starting BLOCKING Controller!");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Quote>> response = restTemplate.exchange(
                URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Quote>>(){});

        List<Quote> result = response.getBody();
        result.forEach(quote -> System.out.println(quote.toString()));
        System.out.println("Exiting BLOCKING Controller!");
        return result;
    }

    @GetMapping(value = "/quotes-non-blocking",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public List<Quote> getquotesNonBlocking() {
        System.out.println("Starting NON-BLOCKING Controller!");
        List<Quote> result = WebClient.create()
                .get()
                .uri(URI)
                .retrieve()
                .bodyToFlux(Quote.class)
                .collectList()
                .block();

        result.forEach(quote -> System.out.println(quote.toString()));
        System.out.println("Exiting NON-BLOCKING Controller!");
        return result;
    }
}
