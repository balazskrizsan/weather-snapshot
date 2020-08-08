package com.kbalazsworks.weathersnapshot.entity;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

public class ParsedTemperatureSnapshot
{
    final private @Nullable Long          id;
    final private           Long          htmlLogsId;
    final private           LocalDateTime temperatureTime;
    final private           Integer       temperatureMin;
    final private           Integer       temperatureMax;

    public ParsedTemperatureSnapshot(
        @Nullable Long id,
        Long htmlLogsId,
        LocalDateTime temperatureTime,
        Integer temperatureMin,
        Integer temperatureMax
    )
    {
        this.id              = id;
        this.htmlLogsId      = htmlLogsId;
        this.temperatureTime = temperatureTime;
        this.temperatureMin  = temperatureMin;
        this.temperatureMax  = temperatureMax;
    }

    public @Nullable Long getId()
    {
        return id;
    }

    public Long getHtmlLogsId()
    {
        return htmlLogsId;
    }

    public LocalDateTime getTemperatureTime()
    {
        return temperatureTime;
    }

    public Integer getTemperatureMin()
    {
        return temperatureMin;
    }

    public Integer getTemperatureMax()
    {
        return temperatureMax;
    }
}
