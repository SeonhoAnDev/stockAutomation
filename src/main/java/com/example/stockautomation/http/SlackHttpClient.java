package com.example.stockautomation.http;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import org.springframework.stereotype.Component;

@Component
public class SlackHttpClient {
    private static final String WEBHOOK_URL = "https://hooks.slack.com/services/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";

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
