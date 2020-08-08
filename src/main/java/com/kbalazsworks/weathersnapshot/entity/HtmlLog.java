package com.kbalazsworks.weathersnapshot.entity;

import com.kbalazsworks.weathersnapshot.enums.SiteEnum;
import com.kbalazsworks.weathersnapshot.enums.SiteUriEnum;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

public class HtmlLog
{
    final private @Nullable Long          id;
    final private           SiteEnum      siteId;
    final private           SiteUriEnum   siteUriId;
    final private           int           parserVersionId;
    final private           String        html;
    final private           LocalDateTime createdAt;

    public HtmlLog(
        @Nullable Long id,
        SiteEnum siteId,
        SiteUriEnum siteUriId,
        int parserVersionId,
        String html,
        LocalDateTime createdAt
    )
    {
        this.id              = id;
        this.siteId          = siteId;
        this.siteUriId       = siteUriId;
        this.parserVersionId = parserVersionId;
        this.html            = html;
        this.createdAt       = createdAt;
    }

    public @Nullable Long getId()
    {
        return id;
    }

    public SiteEnum getSiteId()
    {
        return siteId;
    }

    public SiteUriEnum getSiteUriId()
    {
        return siteUriId;
    }

    public int getParserVersionId()
    {
        return parserVersionId;
    }

    public String getHtml()
    {
        return html;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }
}
