package com.kbalazsworks.weathersnapshot.integration.services;

import com.kbalazsworks.weathersnapshot.MockFactory;
import com.kbalazsworks.weathersnapshot.RecordMockFactory;
import com.kbalazsworks.weathersnapshot.entity.ParsedTemperatureSnapshot;
import com.kbalazsworks.weathersnapshot.integration.AbstractIntegrationTest;
import com.kbalazsworks.weathersnapshot.service.ParserService;
import com.kbalazsworks.weathersnapshot.utils.services.DateTimeService;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Result;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

public class ParserServiceTest extends AbstractIntegrationTest
{
    @Autowired
    private ParserService parserService;

    @Autowired
    private DateTimeService dateTimeService;

    @Test
    @SqlGroup(
        {
            @Sql(
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED),
                scripts = {"classpath:test/sqls/truncate_tables.sql", "classpath:test/sqls/set_3.sql"}
            ),
            @Sql(
                executionPhase = AFTER_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED),
                scripts = {"classpath:test/sqls/truncate_tables.sql"}
            ),
        }
    )
    public void start_parserAllRecord_auccessfulSave() throws ParseException
    {
        // Arrange
        DSLContext qB = getQueryBuilder();

        parserService.setDateFactory(MockFactory.getMockDateFactoryFromDateTime("2020-12-30 12:13:14"));

        Result<Record4<Long, LocalDateTime, Integer, Integer>> expectedResult = RecordMockFactory
            .getParsedTemperatureSnapshotsMockWithNullId(
                qB, new ArrayList<>()
                {{
                    add(new ParsedTemperatureSnapshot(
                        null,
                        1L,
                        MockFactory.getLocalDateTimeMockFromDateTime("2020-12-30 00:00:00"),
                        2,
                        1
                    ));
                    add(new ParsedTemperatureSnapshot(
                        null,
                        1L,
                        MockFactory.getLocalDateTimeMockFromDateTime("2020-12-31 00:00:00"),
                        4,
                        3
                    ));
                    add(new ParsedTemperatureSnapshot(
                        null,
                        1L,
                        MockFactory.getLocalDateTimeMockFromDateTime("2021-01-01 00:00:00"),
                        6,
                        5
                    ));
                }}
            );

        Result<Record1<LocalDateTime>> expectedHtmlLogResult = RecordMockFactory
            .getHtmlLogRecordMockWithOnlyParsedAt(
                qB, new ArrayList<>()
                {{
                    add(MockFactory.getLocalDateTimeMockFromDateTime("2020-12-30 12:13:14"));
                }}
            );

        // Act
        parserService.start();

        // Assert
        Result<Record4<Long, LocalDateTime, Integer, Integer>> result = qB
            .select(
                parsedTemperatureSnapshotsTable.HTML_LOGS_ID,
                parsedTemperatureSnapshotsTable.TEMPERATURE_TIME,
                parsedTemperatureSnapshotsTable.TEMPERATURE_MIN,
                parsedTemperatureSnapshotsTable.TEMPERATURE_MAX
            )
            .from(parsedTemperatureSnapshotsTable)
            .fetch();

        Result<Record1<LocalDateTime>> htmlLogsResult = qB.select(htmlLogsTable.PARSED).from(htmlLogsTable).fetch();

        assertAll(
            () -> Assert.assertArrayEquals(expectedResult.toArray(), result.toArray()),
            () -> Assert.assertArrayEquals(expectedHtmlLogResult.toArray(), htmlLogsResult.toArray())
        );

    }
}
