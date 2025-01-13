package com.example.stockautomation.service;

import com.example.stockautomation.http.SlackHttpClient;
import com.example.stockautomation.http.StockHttpClient;
import com.example.stockautomation.dto.StockTickerDto;
import com.example.stockautomation.dto.BusinessDayResultDto;
import com.example.stockautomation.repository.ReportHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@RequiredArgsConstructor
public class StockSlackService {
    private final StockHttpClient stockHttpClient;
    private final BusinessDayService businessDayService;
    private final ReportHistoryRepository repository;

    @SneakyThrows
    public void execute(String stock){
        StockTickerDto tickerByStock = stockHttpClient.getTickerByStock(stock);
        BusinessDayResultDto formattedDate = businessDayService.getPreviousBusinessDay();

        String sb = formattedDate.dayType() +
                "\n" +
                "[銘柄コード_"+
                stock + "]" +
                " price = " +
                tickerByStock.getClose();
            SlackHttpClient.sendSlackMessage(sb);

            repository.save(
                    stock,
                    String.valueOf(tickerByStock.getOpen()),
                    String.valueOf(tickerByStock.getClose()),
                    String.valueOf(tickerByStock.getHigh()),
                    String.valueOf(tickerByStock.getLow()));
    }
}
