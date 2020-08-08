package com.kbalazsworks.weathersnapshot.entity;

import com.kbalazsworks.weathersnapshot.enums.HttpMethodEnum;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

public class SiteUri
{
    final private Integer        id;
    final private int            siteId;
    final private int            siteUriId;
    final private int            latestParserVersionId;
    final private HttpMethodEnum method;
    final private String         uri;
    @Nullable
    final private JSONObject     params;

    public SiteUri(
        Integer id, int siteId, int siteUriId, int latestParserVersionId, HttpMethodEnum method, String uri,
        @Nullable JSONObject params
    )
    {
        this.id                     = id;
        this.siteId                 = siteId;
        this.siteUriId             = siteUriId;
        this.latestParserVersionId = latestParserVersionId;
        this.method                = method;
        this.uri                    = uri;
        this.params                 = params;
    }

    public Integer getId()
    {
        return id;
    }

    public int getSiteId()
    {
        return siteId;
    }

    public int getSiteUriId()
    {
        return siteUriId;
    }

    public int getLatestParserVersionId()
    {
        return latestParserVersionId;
    }

    public HttpMethodEnum getMethod()
    {
        return method;
    }

    public String getUri()
    {
        return uri;
    }

    public @Nullable JSONObject getParams()
    {
        return params;
    }
}
