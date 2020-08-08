package com.kbalazsworks.weathersnapshot.factories;

import com.kbalazsworks.weathersnapshot.db.tables.records.HtmlLogsRecord;
import com.kbalazsworks.weathersnapshot.entity.HtmlLog;
import com.kbalazsworks.weathersnapshot.enums.SiteEnum;
import com.kbalazsworks.weathersnapshot.enums.SiteUriEnum;
import com.kbalazsworks.weathersnapshot.utils.services.DateTimeService;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlLogEntityFactory
{
    private DateTimeService dateTimeService;

    @Autowired
    public void setDateTimeService(DateTimeService dateTimeService)
    {
        this.dateTimeService = dateTimeService;
    }

    public List<HtmlLog> bulkBuild(Result<HtmlLogsRecord> result)
    {
        List<HtmlLog> htmlLogs = new ArrayList<>();

        result.forEach(
            record -> {
                htmlLogs.add(new HtmlLog(
                    record.getId(),
                    SiteEnum.getByValue(record.getSiteId()),
                    SiteUriEnum.getByValue(record.getSiteUriId()),
                    record.getParserVersionId(),
                    record.getHtml(),
                    record.getCreatedAt()
                ));
            }
        );

        return htmlLogs;
    }
}

