package com.example.stockautomation.http;

import com.example.stockautomation.exception.StockClientException;
import com.example.stockautomation.service.BusinessDayService;
import com.example.stockautomation.service.TokenService;
import com.example.stockautomation.dto.BusinessDayResultDto;
import com.example.stockautomation.dto.StockTickerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockHttpClient {
    private final HttpClient httpClient;
    private final TokenService tokenService;
    private final BusinessDayService businessDayService;

    public StockTickerDto getTickerByStock(String code) throws JsonProcessingException {
        try {
            String refreshToken = tokenService.getRefreshToken();
            String idToken = tokenService.getIdToken(refreshToken);
            BusinessDayResultDto formattedDate = businessDayService.getPreviousBusinessDay();

            String url = "https://api.jquants.com/v1/prices/daily_quotes?code=" + code + "&date=" + formattedDate.date();
            String execute = httpClient.execute(url, HttpMethod.GET, "", idToken);
            return parseDailyQuotes(execute);
        } catch (StockClientException e) {
            throw new StockClientException(e.getMessage());
        }
    };

    private StockTickerDto parseDailyQuotes(String execute) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JSONObject jsonResponse = new JSONObject(execute);
            String dailyQuotesJson = jsonResponse.getJSONArray("daily_quotes").toString();
            List<StockTickerDto> dailyQuotes = objectMapper.readValue(
                    dailyQuotesJson,
                    new TypeReference<List<StockTickerDto>>() {}
            );
            return dailyQuotes.stream().findFirst().orElseThrow(() -> new StockClientException("No data found"));
        } catch (Exception e) {
            throw new StockClientException(e.getMessage());
        }
    }
}

