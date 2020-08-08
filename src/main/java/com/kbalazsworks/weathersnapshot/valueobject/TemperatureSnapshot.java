package com.kbalazsworks.weathersnapshot.valueobject;

import java.time.LocalDateTime;

public class TemperatureSnapshot
{
    private final Long          htmlLogId;
    private final LocalDateTime temperatureTime;
    private final int           minTemperature;
    private final int           maxTemperature;

    public TemperatureSnapshot(
        Long htmlLogId,
        LocalDateTime temperatureTime,
        int minTemperature,
        int maxTemperature
    )
    {
        this.htmlLogId         = htmlLogId;
        this.temperatureTime   = temperatureTime;
        this.minTemperature    = minTemperature;
        this.maxTemperature    = maxTemperature;
    }

    public Long getHtmlLogId()
    {
        return htmlLogId;
    }

    public LocalDateTime getTemperatureTime()
    {
        return temperatureTime;
    }

    public int getMinTemperature()
    {
        return minTemperature;
    }

    public int getMaxTemperature()
    {
        return maxTemperature;
    }
}
