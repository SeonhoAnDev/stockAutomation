package com.example.stockautomation.http;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import jakarta.annotation.PostConstruct;


@Component
public class SlackHttpClient {

    @Value("${slack.key}")
    private String webhookUrl;

    private static String WEBHOOK_URL;

    @PostConstruct
    public void init() {
        WEBHOOK_URL = this.webhookUrl;
    }


    public static void sendSlackMessage(String message) {
        try {
            Slack instance = Slack.getInstance();
            Payload payload = Payload.builder()
                    .text(message)
                    .build();
            instance.send(WEBHOOK_URL, payload); // 정적 변수 사용
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
