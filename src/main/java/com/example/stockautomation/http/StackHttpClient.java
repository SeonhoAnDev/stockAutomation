package com.example.stockautomation.http;

import com.example.stockautomation.exception.StockClientException;
import com.example.stockautomation.service.BusinessDayService;
import com.example.stockautomation.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StackHttpClient {
    private final HttpClient httpClient;
    private final TokenService tokenService;
    private final BusinessDayService businessDayService;

    public Object getTickerByMarket(String code) throws JsonProcessingException {
        String refreshToken = tokenService.getRefreshToken();
        String idToken = tokenService.getIdToken(refreshToken);
        LocalDate date = LocalDate.now().minusWeeks(13);
        String formattedDate = businessDayService.getPreviousBusinessDay(date);

        String url = "https://api.jquants.com/v1/prices/daily_quotes?code=" + code + "&date=" + formattedDate;
        String execute = httpClient.execute(url, HttpMethod.GET, "", idToken);
        return parseDailyQuotes(execute);
    }

    private Object parseDailyQuotes(String execute) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JSONObject jsonResponse = new JSONObject(execute);
            String dailyQuotesJson = jsonResponse.getJSONArray("daily_quotes").toString();
            List<StackTickerDto> dailyQuotes = objectMapper.readValue(
                    dailyQuotesJson,
                    new TypeReference<List<StackTickerDto>>() {}
            );
            return dailyQuotes.stream().findFirst().orElseThrow(() -> new StockClientException("No data found"));
        } catch (Exception e) {
            throw new StockClientException(e.getMessage());
        }
    }
}


