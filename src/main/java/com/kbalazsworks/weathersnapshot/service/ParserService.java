package com.kbalazsworks.weathersnapshot.service;

import com.kbalazsworks.weathersnapshot.exception.ParserException;
import com.kbalazsworks.weathersnapshot.factories.ParserFactory;
import com.kbalazsworks.weathersnapshot.iface.ParserStrategyInterface;
import com.kbalazsworks.weathersnapshot.utils.factories.DateFactory;
import com.kbalazsworks.weathersnapshot.utils.factories.Slf4jLoggerFactory;
import com.kbalazsworks.weathersnapshot.valueobject.ParserResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserService
{
    private Logger                            logger;
    private HtmlLogService                    htmlLogService;
    private ParserFactory                     parserFactory;
    private ParsedTemperatureSnapshotsService parsedTemperatureSnapshotsService;
    private DateFactory                       dateFactory;

    @Autowired
    public void setLogger(Slf4jLoggerFactory logger)
    {
        this.logger = logger.create(ParserService.class);
    }

    @Autowired
    public void setHtmlLogService(HtmlLogService htmlLogService)
    {
        this.htmlLogService = htmlLogService;
    }

    @Autowired
    public void setParserFactory(ParserFactory parserFactory)
    {
        this.parserFactory = parserFactory;
    }

    @Autowired
    public void setParsedTemperatureSnapshots(ParsedTemperatureSnapshotsService parsedTemperatureSnapshotsService)
    {
        this.parsedTemperatureSnapshotsService = parsedTemperatureSnapshotsService;
    }

    @Autowired
    public void setDateFactory(DateFactory dateFactory)
    {
        this.dateFactory = dateFactory;
    }

    /**
     * Test:
     * 3 parserhez 3 HTML a db-be
     * 5 retek mindharomba, uj evere is csusszon at
     * csekkolni, hogy a parsed-be a 15 rekord jol kerult-e be
     */
    public void start()
    {
        htmlLogService.searchNotParsed().forEach(
            htmlLog ->
            {
                String siteName    = String.valueOf(htmlLog.getSiteId()).toLowerCase();
                String siteUriName = String.valueOf(htmlLog.getSiteUriId()).toLowerCase();

                StringBuilder parserClass = new StringBuilder();
                for (String part : StringUtils.splitByCharacterTypeCamelCase(siteUriName))
                {
                    if (part.equals("_"))
                    {
                        continue;
                    }
                    parserClass.append(StringUtils.capitalize(part));
                }

                String parserName = siteName
                    .concat("/")
                    .concat(parserClass.toString()).replace("Uri", "")
                    .concat("/")
                    .concat(String.valueOf(htmlLog.getSiteUriId().getName()));

                logger.info(parserName);

                try
                {
                    ParserStrategyInterface parser = parserFactory.create(parserName);

                    ParserResponse response = parser.parse(htmlLog.getHtml(), htmlLog.getId(), dateFactory);

                    parsedTemperatureSnapshotsService.bulkInsert(response.getTemperatureSnapshots());

                    htmlLogService.markRecordParsed(htmlLog.getId(), dateFactory.create());
                }
                catch (ParserException e)
                {
                    logger.error("Parser error: ".concat(e.getMessage()));
                }
            }
        );
    }
}
