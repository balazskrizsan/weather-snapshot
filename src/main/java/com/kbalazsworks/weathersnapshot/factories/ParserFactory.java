package com.kbalazsworks.weathersnapshot.factories;

import com.kbalazsworks.weathersnapshot.exception.ParserException;
import com.kbalazsworks.weathersnapshot.iface.ParserStrategyInterface;
import com.kbalazsworks.weathersnapshot.strategy.parser.idokep.Parser30NaposV1Strategy;
import com.kbalazsworks.weathersnapshot.strategy.parser.idokep.ParserForecastV1Strategy;
import com.kbalazsworks.weathersnapshot.strategy.parser.idokep.ParserIdojarasElorejelzesV1Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParserFactory
{
    private Parser30NaposV1Strategy             parser30NaposV1Strategy;
    private ParserForecastV1Strategy            parserForecastV1Strategy;
    private ParserIdojarasElorejelzesV1Strategy parserIdojarasElorejelzesV1Strategy;

    @Autowired
    public void setParser30NaposV1Strategy(Parser30NaposV1Strategy parser30NaposV1Strategy)
    {
        this.parser30NaposV1Strategy = parser30NaposV1Strategy;
    }

    @Autowired
    public void setParserForecastV1Strategy(ParserForecastV1Strategy parserForecastV1Strategy)
    {
        this.parserForecastV1Strategy = parserForecastV1Strategy;
    }

    @Autowired
    public void setParserIdojarasElorejelzesV1Strategy(
        ParserIdojarasElorejelzesV1Strategy parserIdojarasElorejelzesV1Strategy
    )
    {
        this.parserIdojarasElorejelzesV1Strategy = parserIdojarasElorejelzesV1Strategy;
    }

    public ParserStrategyInterface create(String parser) throws ParserException
    {
        switch (parser)
        {
            case "idokep/Elorejelzes/1":
                return parserForecastV1Strategy;
        }

        throw new ParserException("Parser not found: ".concat(parser));
    }
}
