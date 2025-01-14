package com.example.stockautomation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StockTickerDto(
        @JsonProperty("Date") String date, // 日付 (YYYY-MM-DD)
        @JsonProperty("Code") String code, // 銘柄コード
        @JsonProperty("Open") Number open, // 始値（調整前）
        @JsonProperty("High") Number high, // 高値（調整前）
        @JsonProperty("Low") Number low, // 安値（調整前）
        @JsonProperty("Close") Number close, // 終値（調整前）
        @JsonProperty("UpperLimit") String upperLimit, // 日通ストップ高を記録したか、否かを表すフラグ (0：ストップ高以外, 1：ストップ高)
        @JsonProperty("LowerLimit") String lowerLimit, // 日通ストップ安を記録したか、否かを表すフラグ (0：ストップ安以外, 1：ストップ安)
        @JsonProperty("Volume") Number volume, // 取引高（調整前）
        @JsonProperty("TurnoverValue") Number turnoverValue, // 取引代金
        @JsonProperty("AdjustmentFactor") Number adjustmentFactor, // 調整係数
        @JsonProperty("AdjustmentOpen") Number adjustmentOpen, // 調整済み始値
        @JsonProperty("AdjustmentHigh") Number adjustmentHigh, // 調整済み高値
        @JsonProperty("AdjustmentLow") Number adjustmentLow, // 調整済み安値
        @JsonProperty("AdjustmentClose") Number adjustmentClose, // 調整済み終値
        @JsonProperty("AdjustmentVolume") Number adjustmentVolume // 調整済み取引高
) {}
