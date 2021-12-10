package com.voxsmart.suite.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Builder
@Value
@With
public class HistoricalDataRow {
    private final String date;
    private final String open;
    private final String high;
    private final String low;
    private final String close;
    private final String volume;
    private final String marketCap;
}
