package com.kbalazsworks.weathersnapshot.integration.services;

import com.kbalazsworks.weathersnapshot.MockFactory;
import com.kbalazsworks.weathersnapshot.RecordMockFactory;
import com.kbalazsworks.weathersnapshot.db.tables.HtmlLogs;
import com.kbalazsworks.weathersnapshot.entity.HtmlLog;
import com.kbalazsworks.weathersnapshot.enums.SiteEnum;
import com.kbalazsworks.weathersnapshot.enums.SiteUriEnum;
import com.kbalazsworks.weathersnapshot.integration.AbstractIntegrationTest;
import com.kbalazsworks.weathersnapshot.service.HtmlLogService;
import com.kbalazsworks.weathersnapshot.utils.factories.DateFactory;
import com.kbalazsworks.weathersnapshot.utils.services.DateTimeService;
import org.jooq.DSLContext;
import org.jooq.Record5;
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
import java.util.Date;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

public class HtmlLogServiceTest extends AbstractIntegrationTest
{


    @Autowired
    private DateFactory dateFactory;

    @Autowired
    private DateTimeService dateTimeService;

    @Autowired
    private HtmlLogService htmlLogService;

    @Test
    @SqlGroup(
        {
            @Sql(
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED),
                scripts = {"classpath:test/sqls/truncate_tables.sql", "classpath:test/sqls/set_1.sql"}
            ),
            @Sql(
                executionPhase = AFTER_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED),
                scripts = {"classpath:test/sqls/truncate_tables.sql"}
            ),
        }
    )
    public void insert_validEntity_successfulInsert() throws ParseException
    {
        // Arrange
        DSLContext qB = getQueryBuilder();

        LocalDateTime now = MockFactory.getLocalDateTimeMockFromDateTime("2020-01-01 00:11:22");

        int  testedSiteId = 1;
        HtmlLog testedHtmlLog = new HtmlLog(
            null,
            SiteEnum.getByValue(1),
            SiteUriEnum.getByValue(2),
            1,
            "<sala></sala>",
            now
        );

        Result<Record5<Integer, Integer, Integer, String, LocalDateTime>> expectedResult = RecordMockFactory
            .getHtmlLogRecordMockWithNullId(
                qB, new ArrayList<>()
                {{
                    add(new HtmlLog(
                        null,
                        SiteEnum.getByValue(1),
                        SiteUriEnum.getByValue(2),
                        1,
                        "<sala></sala>",
                        now
                    ));
                }}
            );

        // Act
        htmlLogService.insert(testedHtmlLog);

        // Assert
        Result<Record5<Integer, Integer, Integer, String, LocalDateTime>> record = qB
            .select(
                htmlLogsTable.SITE_ID,
                htmlLogsTable.SITE_URI_ID,
                htmlLogsTable.PARSER_VERSION_ID,
                htmlLogsTable.HTML,
                htmlLogsTable.CREATED_AT
            )
            .from(htmlLogsTable)
            .where(htmlLogsTable.SITE_ID.eq(testedSiteId))
            .limit(1)
            .fetch();

        Assert.assertArrayEquals(record.toArray(), expectedResult.toArray());
    }
}
