/*
 * This file is generated by jOOQ.
 */
package com.kbalazsworks.weathersnapshot.db;


import com.kbalazsworks.weathersnapshot.db.tables.FlywaySchemaHistory;
import com.kbalazsworks.weathersnapshot.db.tables.HtmlLogs;
import com.kbalazsworks.weathersnapshot.db.tables.ParsedTemperatureSnapshots;
import com.kbalazsworks.weathersnapshot.db.tables.SiteUris;
import com.kbalazsworks.weathersnapshot.db.tables.Sites;


/**
 * Convenience access to all tables in public
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public static final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.html_logs</code>.
     */
    public static final HtmlLogs HTML_LOGS = HtmlLogs.HTML_LOGS;

    /**
     * The table <code>public.parsed_temperature_snapshots</code>.
     */
    public static final ParsedTemperatureSnapshots PARSED_TEMPERATURE_SNAPSHOTS = ParsedTemperatureSnapshots.PARSED_TEMPERATURE_SNAPSHOTS;

    /**
     * The table <code>public.site_uris</code>.
     */
    public static final SiteUris SITE_URIS = SiteUris.SITE_URIS;

    /**
     * The table <code>public.sites</code>.
     */
    public static final Sites SITES = Sites.SITES;
}