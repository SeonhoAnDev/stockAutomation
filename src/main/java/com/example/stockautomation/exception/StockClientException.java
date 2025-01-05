package com.example.stockautomation.exception;

public class StockClientException extends RuntimeException {
    public StockClientException(String errorMessage) {
        super(String.format("日経指数の呼出実行中でのエラー. error=%s", errorMessage));
    }
}
