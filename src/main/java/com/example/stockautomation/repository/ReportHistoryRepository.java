package com.example.stockautomation.repository;

import com.example.stockautomation.entity.ReportHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReportHistoryRepository {
    private final ReportHistoryJpaRepository repository;

    public void save(String stock, String openPrice, String closePrice, String highPrice, String lowPrice){
        repository.save(
                new ReportHistory(stock, openPrice, closePrice, highPrice, lowPrice)
        );
    }
}
