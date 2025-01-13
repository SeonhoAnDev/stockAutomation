package com.example.stockautomation.service;

import com.example.stockautomation.dto.StockTickerDto;
import com.example.stockautomation.dto.BusinessDayResultDto;
import com.example.stockautomation.http.SlackHttpClient;
import com.example.stockautomation.http.StockHttpClient;
import com.example.stockautomation.repository.ReportHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockSlackServiceTest {
    @Mock
    private StockHttpClient stockHttpClient;

    @Mock
    private ReportHistoryRepository reportHistoryRepository;

    @Mock
    private BusinessDayService businessDayService;

    @InjectMocks
    private StockSlackService sut;

    @Test
    void test() throws Exception {
        //given
        String stock = "9449";
        when(stockHttpClient.getTickerByStock(stock))
                .thenReturn(StockTickerDto.builder()
                        .close(10)
                        .low(10)
                        .high(10)
                        .open(10)
                        .build());

        when(businessDayService.getPreviousBusinessDay())
                .thenReturn(new BusinessDayResultDto("2025-01-12", "平日"));

        try (MockedStatic<SlackHttpClient> mockedSlackClient = mockStatic(SlackHttpClient.class)) {
            //when
            sut.execute(stock);

            //then
            verify(stockHttpClient, atMostOnce()).getTickerByStock(stock);
            verify(businessDayService, atMostOnce()).getPreviousBusinessDay();
            mockedSlackClient.verify(() -> SlackHttpClient.sendSlackMessage(any(String.class)), atMostOnce());
            verify(reportHistoryRepository, atMostOnce()).save(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class));
        }
    }
}
