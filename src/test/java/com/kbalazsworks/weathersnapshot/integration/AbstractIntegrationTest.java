package com.kbalazsworks.weathersnapshot.integration;

import com.kbalazsworks.weathersnapshot.AbstractTest;
import com.kbalazsworks.weathersnapshot.db.tables.HtmlLogs;
import com.kbalazsworks.weathersnapshot.db.tables.ParsedTemperatureSnapshots;
import com.kbalazsworks.weathersnapshot.utils.services.JooqService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractIntegrationTest extends AbstractTest
{
    final protected HtmlLogs htmlLogsTable = HtmlLogs.HTML_LOGS;

    final protected ParsedTemperatureSnapshots parsedTemperatureSnapshotsTable
        = ParsedTemperatureSnapshots.PARSED_TEMPERATURE_SNAPSHOTS;

    @Autowired
    private JooqService jooqService;

    protected DSLContext getQueryBuilder()
    {
        return jooqService.createQueryBuilder();
    }
}
