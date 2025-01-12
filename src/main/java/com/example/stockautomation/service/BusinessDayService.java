package com.example.stockautomation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class BusinessDayService {
    public String getPreviousBusinessDay(LocalDate date) {
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

    private boolean isHoliday(LocalDate date) {
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
