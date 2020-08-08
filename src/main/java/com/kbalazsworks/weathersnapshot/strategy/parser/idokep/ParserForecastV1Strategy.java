package com.kbalazsworks.weathersnapshot.strategy.parser.idokep;

import com.kbalazsworks.weathersnapshot.iface.ParserStrategyInterface;
import com.kbalazsworks.weathersnapshot.service.ParserService;
import com.kbalazsworks.weathersnapshot.utils.factories.DateFactory;
import com.kbalazsworks.weathersnapshot.utils.factories.JsoupParseFactory;
import com.kbalazsworks.weathersnapshot.utils.factories.Slf4jLoggerFactory;
import com.kbalazsworks.weathersnapshot.valueobject.ParserResponse;
import com.kbalazsworks.weathersnapshot.valueobject.TemperatureSnapshot;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class ParserForecastV1Strategy implements ParserStrategyInterface
{
    private JsoupParseFactory jsoupConnectFactory;
    private Logger            logger;

    @Autowired
    public void setJsoupConnectFactory(JsoupParseFactory jsoupConnectFactory)
    {
        this.jsoupConnectFactory = jsoupConnectFactory;
    }

    @Autowired
    public void setLogger(Slf4jLoggerFactory logger)
    {
        this.logger = logger.create(ParserService.class);
    }

    @Override
    public ParserResponse parse(String html, Long htmlLogsId, DateFactory dateFactory)
    {
        Document document = jsoupConnectFactory.create(html);

        Elements elements = document.select(".kartya-full .oszlop");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFactory.create());

        int     year          = calendar.get(Calendar.YEAR);
        int     month         = calendar.get(Calendar.MONTH) + 1;
        int     previousDay   = 0;
        int     previousMonth = 0;
        boolean firstRun      = true;

        List<TemperatureSnapshot> temperatureSnapshots = new ArrayList<>();

        for (Element columns : elements)
        {
            int day = Integer.parseInt(columns.select(".nap-box .datum").first().ownText());
            int max = Integer.parseInt(columns.select("> .max-homerseklet-default").first().ownText());
            int min = Integer.parseInt(columns.select("> .min-homerseklet-default").first().ownText());

            if (firstRun)
            {
                previousDay   = day;
                previousMonth = month;

                firstRun = false;
            }
            if (previousDay > day)
            {
                month++;
                if (month > 12)
                {
                    month = 1;
                }
            }
            if (previousMonth > month)
            {
                year++;
            }

            LocalDateTime temperatureTime = LocalDateTime.of(
                LocalDate.of(year, month, day),
                LocalTime.of(0, 0)
            );

            TemperatureSnapshot temperatureSnapshot = new TemperatureSnapshot(htmlLogsId, temperatureTime, min, max);

            temperatureSnapshots.add(temperatureSnapshot);
            previousDay = day;
        }

        return new ParserResponse(temperatureSnapshots);
    }
}
