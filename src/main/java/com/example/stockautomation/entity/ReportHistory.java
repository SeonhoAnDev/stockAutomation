package com.example.stockautomation.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "report_histories")
public class ReportHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stock_code")
    private String stockCode;

    @Column(name = "open_price")
    private String openPrice;

    @Column(name = "close_price")
    private String closePrice;

    @Column(name = "high_price")
    private String highPrice;

    @Column(name = "low_price")
    private String lowPrice;

    @Column(name = "reported_at")
    private LocalDateTime reportedAt;

    public ReportHistory(String stockCode, String openPrice, String closePrice, String highPrice, String lowPrice) {
        this.stockCode = stockCode;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.reportedAt = LocalDateTime.now();
    }

}
