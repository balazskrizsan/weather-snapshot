package com.kbalazsworks.weathersnapshot.repository;

import com.kbalazsworks.weathersnapshot.db.tables.records.SiteUrisRecord;
import com.kbalazsworks.weathersnapshot.entity.SiteUri;
import com.kbalazsworks.weathersnapshot.enums.HttpMethodEnum;
import com.kbalazsworks.weathersnapshot.utils.factories.Slf4jLoggerFactory;
import org.jooq.JSON;
import org.jooq.Record8;
import org.jooq.Result;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SiteUriEntityBuilder
{

    private Logger logger;

    @Autowired
    public void setLogger(Slf4jLoggerFactory logger)
    {
        this.logger = logger.create(SiteUriEntityBuilder.class);
    }

    public ArrayList<SiteUri> bulkBuild(Result<SiteUrisRecord> records)
    {
        ArrayList<SiteUri> siteUris = new ArrayList<>();

        for (SiteUrisRecord record : records)
        {
            try
            {
                siteUris.add(build(record));
            }
            catch (IllegalAccessException e)
            {
                this.logger.error("Rarsing error, on record", e);
                this.logger.error("", record);
            }
        }

        return siteUris;
    }

    public SiteUri build(SiteUrisRecord siteUrisRecord) throws IllegalAccessException
    {
        return new SiteUri(
            siteUrisRecord.getId(),
            siteUrisRecord.getSiteId(),
            siteUrisRecord.getSiteUriId(),
            siteUrisRecord.getLatestParserVersionId(),
            HttpMethodEnum.getByValue(siteUrisRecord.getMethod()),
            siteUrisRecord.getUri(),
            new JSONObject(siteUrisRecord.getParams().toString())
        );
    }

    public SiteUri build(
        Record8<Integer, Integer, Integer, Integer, Integer, String, JSON, String> siteUrisWithDomainRecord
    )
    throws IllegalAccessException
    {
        return new SiteUri(
            siteUrisWithDomainRecord.value1(),
            siteUrisWithDomainRecord.value2(),
            siteUrisWithDomainRecord.value3(),
            siteUrisWithDomainRecord.value4(),
            HttpMethodEnum.getByValue(siteUrisWithDomainRecord.value5()),
            siteUrisWithDomainRecord.value6(),
            null == siteUrisWithDomainRecord.value7()
                ? null
                : new JSONObject(siteUrisWithDomainRecord.value7().toString())
        );
    }

    public ArrayList<SiteUrisWithDomain> bulkBuildSiteUrisWithDomainEntities(
        Result<Record8<Integer, Integer, Integer, Integer, Integer, String, JSON, String>> records
    )
    {
        ArrayList<SiteUrisWithDomain> siteUrisWithDomain = new ArrayList<>();

        records.forEach((record) ->
        {
            try
            {
                siteUrisWithDomain.add(new SiteUrisWithDomain(build(record), record.value8()));
            }
            catch (IllegalAccessException e)
            {
                //@todo: add error handling
            }
        });

        return siteUrisWithDomain;
    }
}
