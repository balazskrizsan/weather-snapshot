package com.kbalazsworks.weathersnapshot.service;

import com.kbalazsworks.weathersnapshot.entity.HtmlLog;
import com.kbalazsworks.weathersnapshot.entity.SiteUri;
import com.kbalazsworks.weathersnapshot.enums.HttpMethodEnum;
import com.kbalazsworks.weathersnapshot.enums.SiteEnum;
import com.kbalazsworks.weathersnapshot.enums.SiteUriEnum;
import com.kbalazsworks.weathersnapshot.exception.DownloadException;
import com.kbalazsworks.weathersnapshot.repository.SiteUrisWithDomain;
import com.kbalazsworks.weathersnapshot.utils.factories.DateFactory;
import com.kbalazsworks.weathersnapshot.utils.factories.JsoupConnectFactory;
import com.kbalazsworks.weathersnapshot.utils.factories.Slf4jLoggerFactory;
import com.kbalazsworks.weathersnapshot.utils.services.DateTimeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class DownloaderService
{
    private HtmlLogService htmlLogService;
    private SiteUriService siteUriService;
    private DateFactory dateFactory;
    private DateTimeService dateTimeService;
    private JsoupConnectFactory jsoupConnectFactory;
    private Logger logger;

    @Autowired
    public void setHtmlLogService(HtmlLogService htmlLogService)
    {
        this.htmlLogService = htmlLogService;
    }

    @Autowired
    public void setSiteUriService(SiteUriService siteUriService)
    {
        this.siteUriService = siteUriService;
    }

    @Autowired
    public void setDateFactory(DateFactory dateFactory)
    {
        this.dateFactory = dateFactory;
    }

    @Autowired
    public void setDateTimeService(DateTimeService dateTimeService)
    {
        this.dateTimeService = dateTimeService;
    }

    @Autowired
    public void setJsoupConnectFactory(JsoupConnectFactory jsoupConnectFactory)
    {
        this.jsoupConnectFactory = jsoupConnectFactory;
    }

    @Autowired
    public void setLogger(Slf4jLoggerFactory logger)
    {
        this.logger = logger.create(DownloaderService.class);
    }

    public void startDownload()
    {
        LocalDateTime now = dateTimeService.convertJavaDateToLocalDateTime(dateFactory.create());

        for (SiteUrisWithDomain siteUrisWithDomain : siteUriService.searchWithDomain())
        {
            SiteUri siteUri = siteUrisWithDomain.getSiteUri();
            String url = siteUrisWithDomain.getDomain().concat(siteUri.getUri());

            try
            {
                Document doc = getHtmlBody(jsoupConnectFactory.create(url), siteUri);
                String body = doc.select("body").toString();

                htmlLogService.insert(
                    new HtmlLog(
                        null,
                        SiteEnum.getByValue(siteUri.getSiteId()),
                        SiteUriEnum.getByValue(siteUrisWithDomain.getSiteUri().getSiteUriId()),
                        siteUrisWithDomain.getSiteUri().getLatestParserVersionId(),
                        body,
                        now
                    )
                );

                logger.info("Download from: ".concat(siteUri.getMethod().toString()).concat("|").concat(url));

            } catch (IOException | DownloadException e)
            {
                logger.error("Download error on: ".concat(url), e);
            }
        }
    }

    private Document getHtmlBody(Connection connection, SiteUri siteUri) throws IOException, DownloadException
    {
        JSONObject params = siteUri.getParams();
        if (null != params)
        {
            JSONArray keys = params.names();
            for (int i = 0; i < keys.length(); ++i)
            {
                String key = keys.getString(i);
                connection.data(key, params.getString(key));
            }
        }

        if (siteUri.getMethod() == HttpMethodEnum.GET)
        {
            return connection.get();
        }

        if (siteUri.getMethod() == HttpMethodEnum.POST)
        {
            return connection.post();
        }

        throw new DownloadException("Unhandled Method");
    }
}
