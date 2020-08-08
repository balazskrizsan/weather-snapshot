package com.kbalazsworks.weathersnapshot.repository;

import com.kbalazsworks.weathersnapshot.db.tables.HtmlLogs;
import com.kbalazsworks.weathersnapshot.entity.HtmlLog;
import com.kbalazsworks.weathersnapshot.factories.HtmlLogEntityFactory;
import com.kbalazsworks.weathersnapshot.utils.services.DateTimeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class HtmlLogRepository extends AbstractRepository
{
    private final HtmlLogs             htmlLogsTable = HtmlLogs.HTML_LOGS;
    private       HtmlLogEntityFactory htmlLogEntityFactory;
    private       DateTimeService      dateTimeService;

    @Autowired
    public void setHtmlLogEntityFactory(HtmlLogEntityFactory htmlLogEntityFactory)
    {
        this.htmlLogEntityFactory = htmlLogEntityFactory;
    }

    @Autowired
    public void setDateTimeService(DateTimeService dateTimeService)
    {
        this.dateTimeService = dateTimeService;
    }

    public void insert(@NotNull HtmlLog htmlLog)
    {
        getQueryBuilder()
            .insertInto(
                htmlLogsTable,
                htmlLogsTable.SITE_ID,
                htmlLogsTable.SITE_URI_ID,
                htmlLogsTable.PARSER_VERSION_ID,
                htmlLogsTable.HTML,
                htmlLogsTable.CREATED_AT
            ).values(
            htmlLog.getSiteId().getName(),
            htmlLog.getSiteUriId().getName(),
            htmlLog.getParserVersionId(),
            htmlLog.getHtml(),
            htmlLog.getCreatedAt()
        ).execute();
    }

    public List<HtmlLog> searchNotParsed()
    {
        return htmlLogEntityFactory.bulkBuild(
            getQueryBuilder().selectFrom(htmlLogsTable).where(htmlLogsTable.PARSED.isNull()).fetch()
        );
    }

    public void markRecordParsed(Long id, Date now)
    {
        getQueryBuilder()
            .update(htmlLogsTable)
            .set(htmlLogsTable.PARSED, dateTimeService.convertJavaDateToLocalDateTime(now))
            .where(htmlLogsTable.ID.eq(id))
            .execute();
    }
}
