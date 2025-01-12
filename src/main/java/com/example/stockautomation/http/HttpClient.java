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

    public String execute(String url, HttpMethod method, String body, String refreshToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Authorization", "Bearer " + refreshToken);
            HttpEntity<String> request = new HttpEntity<>(body, headers);
            return restTemplate.exchange(
                    url,
                    method,
                    request,
                    new ParameterizedTypeReference<String>() {}
            ).getBody();
        } catch (RestClientException e) {
            throw new StockClientException(e.getMessage());
        }
    }
}


