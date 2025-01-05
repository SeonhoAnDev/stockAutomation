package com.example.stockautomation.http;

import com.example.stockautomation.exception.StockClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HttpClient {
    private final RestTemplate restTemplate;

    public String execute(String url, HttpMethod method, HttpHeaders headers) {
        try {
            return restTemplate.exchange(
                    url,
                    method,
                    new HttpEntity<>(headers),
                    new ParameterizedTypeReference<String>() {}
            ).getBody();
        } catch (RestClientException e) {
            throw new StockClientException(e.getMessage());
        }
    }
}

