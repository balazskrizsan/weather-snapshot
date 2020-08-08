package com.kbalazsworks.weathersnapshot.iface;

import com.kbalazsworks.weathersnapshot.utils.factories.DateFactory;
import com.kbalazsworks.weathersnapshot.valueobject.ParserResponse;

public interface ParserStrategyInterface
{
    public ParserResponse parse(String html, Long htmlLogsId, DateFactory dateFactory);
}
