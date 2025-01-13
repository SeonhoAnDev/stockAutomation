package com.example.stockautomation.service;

import com.example.stockautomation.dto.BusinessDayResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class BusinessDayService {
    public BusinessDayResultDto getPreviousBusinessDay() {
        LocalDate date = LocalDate.now().minusWeeks(13);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String dayType = "";

        while (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY || isHoliday(date)) {
            if (dayOfWeek == DayOfWeek.SATURDAY) {
                date = date.minusDays(1);
                dayType = "土曜日なので前日のデータを取得します。";
            } else if (dayOfWeek == DayOfWeek.SUNDAY) {
                date = date.minusDays(2);
                dayType = "日曜日なので前日のデータを取得します。";
            } else if (isHoliday(date)) {
                date = date.minusDays(1);
                dayType = "祝日なので前日のデータを取得します。";
            }
            dayOfWeek = date.getDayOfWeek();
        }
        return new BusinessDayResultDto(date.format(DateTimeFormatter.ofPattern("yyyyMMdd")), dayType);
    }

    private boolean isHoliday(LocalDate date) {
        LocalDate[] holidays = {
                LocalDate.of(2023, 1, 1), // 元日
                LocalDate.of(2024, 10, 14) // クリスマス
        };
        for (LocalDate holiday : holidays) {
            if (date.equals(holiday)) {
                return true;
            }
        }
        return false;
    }

}


