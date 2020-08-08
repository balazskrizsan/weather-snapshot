package com.kbalazsworks.weathersnapshot.strategy.parser.idokep;

import com.kbalazsworks.weathersnapshot.iface.ParserStrategyInterface;
import com.kbalazsworks.weathersnapshot.utils.factories.DateFactory;
import com.kbalazsworks.weathersnapshot.valueobject.ParserResponse;
import com.kbalazsworks.weathersnapshot.valueobject.TemperatureSnapshot;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParserIdojarasElorejelzesV1Strategy implements ParserStrategyInterface
{
    @Override
    public ParserResponse parse(String html, Long htmlLogsId, DateFactory dateFactory)
    {
        List<TemperatureSnapshot> temperatureSnapshots = new ArrayList<>();

        return new ParserResponse(temperatureSnapshots);
    }
}
