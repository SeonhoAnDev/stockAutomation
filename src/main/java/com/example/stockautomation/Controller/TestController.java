package com.example.stockautomation.Controller;

import com.example.stockautomation.http.StackHttpClient;
import com.example.stockautomation.service.GmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final StackHttpClient stackHttpClient;

    @GetMapping("/api/vi/ticker/{code}")
    public void test(@PathVariable String code) throws Exception {
        stackHttpClient.getTickerByMarket(code);
        String to = "dkstjsgh5@gmail.com";
        String subject = "Test Email";
        String bodyText = "This is a test email sent using Gmail API.";
        GmailService.sendEmail(to, subject, bodyText);
    }
}
