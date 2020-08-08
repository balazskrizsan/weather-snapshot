package com.kbalazsworks.weathersnapshot.repository;

import com.kbalazsworks.weathersnapshot.entity.SiteUri;

public class SiteUrisWithDomain {
    final private SiteUri siteUri;
    final private String domain;

    public SiteUrisWithDomain(SiteUri siteUri, String domain) {
        this.siteUri = siteUri;
        this.domain = domain;
    }

    public SiteUri getSiteUri() {
        return siteUri;
    }

    public String getDomain() {
        return domain;
    }
}
