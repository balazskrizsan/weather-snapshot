package com.kbalazsworks.weathersnapshot.repository;

import com.kbalazsworks.weathersnapshot.db.tables.SiteUris;
import com.kbalazsworks.weathersnapshot.db.tables.Sites;
import org.jooq.JSON;
import org.jooq.Record8;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class SiteUriRepository extends AbstractRepository
{

    final private SiteUris siteUrisTable = SiteUris.SITE_URIS;
    final private Sites sitesTable = Sites.SITES;
    private SiteUriEntityBuilder siteUriEntityBuilder;

    @Autowired
    public void setSiteUriEntityBuilder(SiteUriEntityBuilder siteUriEntityBuilder)
    {
        this.siteUriEntityBuilder = siteUriEntityBuilder;
    }

    public ArrayList<SiteUrisWithDomain> searchWithDomain()
    {
        Result<Record8<Integer, Integer, Integer, Integer, Integer, String, JSON, String>> result =
            getQueryBuilder()
                .select(
                    siteUrisTable.ID,
                    siteUrisTable.SITE_ID,
                    siteUrisTable.SITE_URI_ID,
                    siteUrisTable.LATEST_PARSER_VERSION_ID,
                    siteUrisTable.METHOD,
                    siteUrisTable.URI,
                    siteUrisTable.PARAMS,
                    sitesTable.DOMAIN
                )
                .from(
                    siteUrisTable
                        .leftJoin(sitesTable)
                        .on(siteUrisTable.SITE_ID.eq(sitesTable.ID))
                )
                .fetch();

        return siteUriEntityBuilder.bulkBuildSiteUrisWithDomainEntities(result);
    }
}
