package com.example.stockautomation.controller;

import com.example.stockautomation.service.StockSlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final StockSlackService stockSlackService;

    @GetMapping("/api/vi/ticker/{code}")
    public void test(@PathVariable String stock) throws Exception {
        stockSlackService.execute(stock);
    }
}




