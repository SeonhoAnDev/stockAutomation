package com.example.stockautomation.service;

import com.example.stockautomation.http.HttpClient;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenService {
    private final HttpClient httpClient;

    @Value("${jquants.email}")
    private String email;

    @Value("${jquants.password}")
    private String password;

    public String getRefreshToken() {
        String url = "https://api.jquants.com/v1/token/auth_user";
        String jsonInputString = String.format("{\"mailaddress\":\"%s\", \"password\":\"%s\"}", email, password);
        String response = httpClient.execute(url, HttpMethod.POST, jsonInputString, "");
        return new JSONObject(response).getString("refreshToken");
    }

    public String getIdToken(String refreshToken) {
        String url = "https://api.jquants.com/v1/token/auth_refresh?refreshtoken=" + refreshToken;
        String response = httpClient.execute(url, HttpMethod.POST, "", "");
        return new JSONObject(response).getString("idToken");
    }
}
