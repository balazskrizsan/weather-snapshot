package com.kbalazsworks.weathersnapshot.valueobject;

import java.util.List;

public class ParserResponse
{
    private final List<TemperatureSnapshot> temperatureSnapshots;

    public ParserResponse(List<TemperatureSnapshot> temperatureSnapshots)
    {
        this.temperatureSnapshots = temperatureSnapshots;
    }

    public List<TemperatureSnapshot> getTemperatureSnapshots()
    {
        return temperatureSnapshots;
    }
}
