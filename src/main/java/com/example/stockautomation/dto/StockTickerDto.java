package com.example.stockautomation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StockTickerDto {
        @JsonProperty("Date")
        private String date; // 日付 (YYYY-MM-DD)

        @JsonProperty("Code")
        private String code; // 銘柄コード

        @JsonProperty("Open")
        private Number open; // 始値（調整前）

        @JsonProperty("High")
        private Number high; // 高値（調整前）

        @JsonProperty("Low")
        private Number low; // 安値（調整前）

        @JsonProperty("Close")
        private Number close; // 終値（調整前）

        @JsonProperty("UpperLimit")
        private String upperLimit; // 日通ストップ高を記録したか、否かを表すフラグ (0：ストップ高以外, 1：ストップ高)

        @JsonProperty("LowerLimit")
        private String lowerLimit; // 日通ストップ安を記録したか、否かを表すフラグ (0：ストップ安以外, 1：ストップ安)

        @JsonProperty("Volume")
        private Number volume; // 取引高（調整前）

        @JsonProperty("TurnoverValue")
        private Number turnoverValue; // 取引代金

        @JsonProperty("AdjustmentFactor")
        private Number adjustmentFactor; // 調整係数

        @JsonProperty("AdjustmentOpen")
        private Number adjustmentOpen; // 調整済み始値

        @JsonProperty("AdjustmentHigh")
        private Number adjustmentHigh; // 調整済み高値

        @JsonProperty("AdjustmentLow")
        private Number adjustmentLow; // 調整済み安値

        @JsonProperty("AdjustmentClose")
        private Number adjustmentClose; // 調整済み終値

        @JsonProperty("AdjustmentVolume")
        private Number adjustmentVolume; // 調整済み取引高
}
