package com.kbalazsworks.weathersnapshot.integration.services;

import com.kbalazsworks.weathersnapshot.MockFactory;
import com.kbalazsworks.weathersnapshot.RecordMockFactory;
import com.kbalazsworks.weathersnapshot.entity.HtmlLog;
import com.kbalazsworks.weathersnapshot.enums.HttpMethodEnum;
import com.kbalazsworks.weathersnapshot.enums.SiteEnum;
import com.kbalazsworks.weathersnapshot.enums.SiteUriEnum;
import com.kbalazsworks.weathersnapshot.integration.AbstractIntegrationTest;
import com.kbalazsworks.weathersnapshot.service.DownloaderService;
import com.kbalazsworks.weathersnapshot.utils.factories.JsoupConnectFactory;
import org.jooq.DSLContext;
import org.jooq.Record5;
import org.jooq.Result;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

public class DownloadServiceTest extends AbstractIntegrationTest
{
    @Autowired
    private DownloaderService downloaderService;

    @Test
    @SqlGroup(
        {
            @Sql(
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED),
                scripts = {"classpath:test/sqls/truncate_tables.sql", "classpath:test/sqls/set_2.sql"}
            ),
            @Sql(
                executionPhase = AFTER_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED),
                scripts = {"classpath:test/sqls/truncate_tables.sql"}
            ),
        }
    )
    public void startDownload_allHtmlAvailable_saveOk() throws IOException, ParseException
    {
        // Arrange
        DSLContext qB = getQueryBuilder();
        downloaderService.setDateFactory(MockFactory.getMockDateFactoryFromDateTime("2018-01-01 12:13:14"));
        downloaderService.setJsoupConnectFactory(getJsoupConnectFactoryMock(
            new ArrayList<>()
            {{
                add(new JsoupConnectionMockData("body 1", HttpMethodEnum.GET, "http://sql2-1.hu/aaa"));
                add(new JsoupConnectionMockData("body 2", HttpMethodEnum.GET, "http://sql2-1.hu/bbb"));
                add(new JsoupConnectionMockData("body 3", HttpMethodEnum.POST, "http://sql2-2.hu/ccc"));
                add(new JsoupConnectionMockData("body 4", HttpMethodEnum.POST, "http://sql2-2.hu/ddd"));
            }}
        ));

        Result<Record5<Integer, Integer, Integer, String, LocalDateTime>> expectedResponse =
            RecordMockFactory.getHtmlLogRecordMockWithNullId(qB, new ArrayList<>()
            {{
                add(new HtmlLog(
                    null,
                    SiteEnum.getByValue(1),
                    SiteUriEnum.getByValue(1),
                    1,
                    "body 1",
                    MockFactory.getLocalDateTimeMockFromDateTime("2018-01-01 12:13:14")
                ));
                add(new HtmlLog(
                    null,
                    SiteEnum.getByValue(1),
                    SiteUriEnum.getByValue(2),
                    1,
                    "body 2",
                    MockFactory.getLocalDateTimeMockFromDateTime("2018-01-01 12:13:14")
                ));
                add(new HtmlLog(
                    null,
                    SiteEnum.getByValue(2),
                    SiteUriEnum.getByValue(1),
                    2,
                    "body 3",
                    MockFactory.getLocalDateTimeMockFromDateTime("2018-01-01 12:13:14")
                ));
                add(new HtmlLog(
                    null,
                    SiteEnum.getByValue(2),
                    SiteUriEnum.getByValue(2),
                    2,
                    "body 4",
                    MockFactory.getLocalDateTimeMockFromDateTime("2018-01-01 12:13:14")
                ));
            }});

        // Act
        downloaderService.startDownload();

        // Assert
        Result<Record5<Integer, Integer, Integer, String, LocalDateTime>> result = qB
            .select(
                htmlLogsTable.SITE_ID,
                htmlLogsTable.SITE_URI_ID,
                htmlLogsTable.PARSER_VERSION_ID,
                htmlLogsTable.HTML,
                htmlLogsTable.CREATED_AT
            )
            .from(htmlLogsTable)
            .orderBy(htmlLogsTable.HTML.asc())
            .fetch();

        Assert.assertArrayEquals(expectedResponse.toArray(), result.toArray());
    }

    private JsoupConnectFactory getJsoupConnectFactoryMock(List<JsoupConnectionMockData> mockDataList) throws
        IOException
    {
        JsoupConnectFactory jsoupConnectFactoryMock = Mockito.mock(JsoupConnectFactory.class);

        for (JsoupConnectionMockData mockData : mockDataList)
        {
            Elements elementsMock = Mockito.mock(Elements.class);
            Mockito
                .when(elementsMock.toString())
                .thenReturn(mockData.getElementsToString());

            Document documentMock = Mockito.mock(Document.class);
            Mockito
                .when(documentMock.select("body"))
                .thenReturn(elementsMock);

            Connection connectionMock = Mockito.mock(Connection.class);
            if (mockData.getHttpMethodEnum() == HttpMethodEnum.GET)
            {
                Mockito
                    .when(connectionMock.get())
                    .thenReturn(documentMock);
            }
            if (mockData.getHttpMethodEnum() == HttpMethodEnum.POST)
            {
                Mockito
                    .when(connectionMock.post())
                    .thenReturn(documentMock);
            }

            Mockito
                .when(jsoupConnectFactoryMock.create(mockData.getFullUrl()))
                .thenReturn(connectionMock);
        }

        return jsoupConnectFactoryMock;
    }

    private static class JsoupConnectionMockData
    {
        private final String         elementsToString;
        private final HttpMethodEnum httpMethodEnum;
        private final String         fullUrl;

        public JsoupConnectionMockData(String elementsToString, HttpMethodEnum httpMethodEnum, String fullUrl)
        {
            this.elementsToString = elementsToString;
            this.httpMethodEnum   = httpMethodEnum;
            this.fullUrl          = fullUrl;
        }

        public String getElementsToString()
        {
            return elementsToString;
        }

        public HttpMethodEnum getHttpMethodEnum()
        {
            return httpMethodEnum;
        }

        public String getFullUrl()
        {
            return fullUrl;
        }
    }
}
