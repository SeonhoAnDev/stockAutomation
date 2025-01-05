package com.example.stockautomation.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.json.JSONObject;

public class JQuantsAPI {

    private static final String REFRESH_TOKEN_URL = "https://api.jquants.com/v1/token/auth_user";
    private static final String ID_TOKEN_URL = "https://api.jquants.com/v1/token/auth_refresh?refreshtoken=";
    private static final String DAILY_QUOTES_URL = "https://api.jquants.com/v1/prices/daily_quotes?code=9449&date=";

    public static void main(String[] args) {
        try {
            // リフレッシュトークンをリクエスト
            String refreshToken = getRefreshToken("", "");

            // IDトークンをリクエスト
            String idToken = getIdToken(refreshToken);

            // 13週間前の日付を計算
            LocalDate date = LocalDate.now().minusWeeks(13);
            String formattedDate = getPreviousBusinessDay(date);

            // 株価データをリクエスト
            String dailyQuotes = getDailyQuotes(idToken, formattedDate);

            // 株価データを出力
            System.out.println("株価データ: " + dailyQuotes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRefreshToken(String email, String password) throws IOException {
        URL url = new URL(REFRESH_TOKEN_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonInputString = "{\"mailaddress\":\"" + email + "\", \"password\":\"" + password + "\"}";
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try (Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8)) {
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            return new JSONObject(response.toString()).getString("refreshToken");
        }
    }

    private static String getIdToken(String refreshToken) throws IOException {
        URL url = new URL(ID_TOKEN_URL + refreshToken);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        try (Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8)) {
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            return new JSONObject(response.toString()).getString("idToken");
        }
    }

    private static String getDailyQuotes(String idToken, String date) throws IOException {
        URL url = new URL(DAILY_QUOTES_URL + date);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + idToken);

        try (Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8)) {
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            return response.toString();
        }
    }

    private static String getPreviousBusinessDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            date = date.minusDays(1);
            System.out.println("土曜日なので前日のデータを取得します。");
        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            date = date.minusDays(2);
            System.out.println("日曜日なので前日のデータを取得します。");
        } else if (isHoliday(date)) {
            date = date.minusDays(1);
            System.out.println("祝日なので前日のデータを取得します。");
        }
        return date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private static boolean isHoliday(LocalDate date) {
        // 祝日リストを定義します。例としていくつかの祝日を追加しました。
        LocalDate[] holidays = {
                LocalDate.of(2023, 1, 1), // 元日
                LocalDate.of(2023, 12, 25) // クリスマス
        };
        for (LocalDate holiday : holidays) {
            if (date.equals(holiday)) {
                return true;
            }
        }
        return false;
    }
}
