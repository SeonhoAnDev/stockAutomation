package com.example.stockautomation.http;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SlackHttpClient {
    @Value("${slack.key}")
    private static String WEBHOOK_URL;

    public static void sendSlackMessage(String message) {
        try {
            Slack instance = Slack.getInstance();
            Payload payload = Payload.builder()
                    .text(message)
                    .build();
            instance.send(WEBHOOK_URL, payload);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
