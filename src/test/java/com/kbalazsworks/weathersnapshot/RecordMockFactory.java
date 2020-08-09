package com.kbalazsworks.weathersnapshot;

import com.kbalazsworks.weathersnapshot.db.tables.HtmlLogs;
import com.kbalazsworks.weathersnapshot.db.tables.ParsedTemperatureSnapshots;
import com.kbalazsworks.weathersnapshot.entity.HtmlLog;
import com.kbalazsworks.weathersnapshot.entity.ParsedTemperatureSnapshot;
import com.kbalazsworks.weathersnapshot.utils.services.DateTimeService;
import org.jooq.*;

import java.time.LocalDateTime;
import java.util.List;

public class RecordMockFactory
{
    final private static HtmlLogs htmlLogsTable = HtmlLogs.HTML_LOGS;
    final private static ParsedTemperatureSnapshots
                                  parsedTemperatureSnapshotsTable
                                                = ParsedTemperatureSnapshots.PARSED_TEMPERATURE_SNAPSHOTS;

    static DateTimeService dateTimeService = new DateTimeService();

    public static Result<Record5<Integer, Integer, Integer, String, LocalDateTime>> getHtmlLogRecordMockWithNullId(
        DSLContext qB, List<HtmlLog> htmlLogs
    )
    {
        Result<Record5<Integer, Integer, Integer, String, LocalDateTime>> result = qB.newResult(
            htmlLogsTable.SITE_ID,
            htmlLogsTable.SITE_URI_ID,
            htmlLogsTable.PARSER_VERSION_ID,
            htmlLogsTable.HTML,
            htmlLogsTable.CREATED_AT
        );
        for (HtmlLog htmlLog : htmlLogs)
        {
            result.add(
                qB
                    .newRecord(
                        htmlLogsTable.SITE_ID,
                        htmlLogsTable.SITE_URI_ID,
                        htmlLogsTable.PARSER_VERSION_ID,
                        htmlLogsTable.HTML,
                        htmlLogsTable.CREATED_AT
                    )
                    .values(
                        htmlLog.getSiteId().getName(),
                        htmlLog.getSiteUriId().getName(),
                        htmlLog.getParserVersionId(),
                        htmlLog.getHtml(),
                        htmlLog.getCreatedAt()
                    )
            );
        }

        return result;
    }

    public static Result<Record1<LocalDateTime>> getHtmlLogRecordMockWithOnlyParsedAt(
        DSLContext qB, List<LocalDateTime> parsedDates
    )
    {
        Result<Record1<LocalDateTime>> result = qB.newResult(htmlLogsTable.PARSED);
        for (LocalDateTime parsedDate : parsedDates)
        {
            result.add(qB.newRecord(htmlLogsTable.PARSED).values(parsedDate));
        }

        return result;
    }

    public static Result<Record4<Long, LocalDateTime, Integer, Integer>>
    getParsedTemperatureSnapshotsMockWithNullId(
        DSLContext qB, List<ParsedTemperatureSnapshot> parsedTemperatureSnapshots
    )
    {
        Result<Record4<Long, LocalDateTime, Integer, Integer>> result = qB.newResult(
            parsedTemperatureSnapshotsTable.HTML_LOGS_ID,
            parsedTemperatureSnapshotsTable.TEMPERATURE_TIME,
            parsedTemperatureSnapshotsTable.TEMPERATURE_MIN,
            parsedTemperatureSnapshotsTable.TEMPERATURE_MAX
        );
        for (ParsedTemperatureSnapshot parsedTemperatureSnapshot : parsedTemperatureSnapshots)
        {
            result.add(
                qB
                    .newRecord(
                        parsedTemperatureSnapshotsTable.HTML_LOGS_ID,
                        parsedTemperatureSnapshotsTable.TEMPERATURE_TIME,
                        parsedTemperatureSnapshotsTable.TEMPERATURE_MIN,
                        parsedTemperatureSnapshotsTable.TEMPERATURE_MAX
                    )
                    .values(
                        parsedTemperatureSnapshot.getHtmlLogsId(),
                        parsedTemperatureSnapshot.getTemperatureTime(),
                        parsedTemperatureSnapshot.getTemperatureMin(),
                        parsedTemperatureSnapshot.getTemperatureMax()
                    )
            );
        }

        return result;
    }

//    public static Result<Record4<Integer, Integer, String, LocalDateTime>> getHtmlLogRecordMockWithNullId(
//            DSLContext qB, int siteId, int siteUrlId, String html, LocalDateTime createdAt
//    ) {
//        Result<Record4<Integer, Integer, String, LocalDateTime>> result = qB.newResult(
//                HtmlLogsTable.SITE_ID,
//                HtmlLogsTable.SITE_URL_ID,
//                HtmlLogsTable.HTML,
//                HtmlLogsTable.CREATED_AT
//        );
//        result.add(
//                qB
//                        .newRecord(
//                                HtmlLogsTable.SITE_ID,
//                                HtmlLogsTable.SITE_URL_ID,
//                                HtmlLogsTable.HTML,
//                                HtmlLogsTable.CREATED_AT
//                        )
//                        .values(siteId, siteUrlId, html, createdAt)
//        );
//
//        return result;
//    }
}
