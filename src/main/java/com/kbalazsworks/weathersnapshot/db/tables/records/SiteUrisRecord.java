/*
 * This file is generated by jOOQ.
 */
package com.kbalazsworks.weathersnapshot.db.tables.records;


import com.kbalazsworks.weathersnapshot.db.tables.SiteUris;

import org.jooq.Field;
import org.jooq.JSON;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SiteUrisRecord extends UpdatableRecordImpl<SiteUrisRecord> implements Record7<Integer, Integer, Integer, Integer, Integer, String, JSON> {

    private static final long serialVersionUID = 39840973;

    /**
     * Setter for <code>public.site_uris.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.site_uris.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.site_uris.site_id</code>.
     */
    public void setSiteId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.site_uris.site_id</code>.
     */
    public Integer getSiteId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.site_uris.site_uri_id</code>.
     */
    public void setSiteUriId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.site_uris.site_uri_id</code>.
     */
    public Integer getSiteUriId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.site_uris.latest_parser_version_id</code>.
     */
    public void setLatestParserVersionId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.site_uris.latest_parser_version_id</code>.
     */
    public Integer getLatestParserVersionId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.site_uris.method</code>.
     */
    public void setMethod(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.site_uris.method</code>.
     */
    public Integer getMethod() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>public.site_uris.uri</code>.
     */
    public void setUri(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.site_uris.uri</code>.
     */
    public String getUri() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.site_uris.params</code>.
     */
    public void setParams(JSON value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.site_uris.params</code>.
     */
    public JSON getParams() {
        return (JSON) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, Integer, Integer, Integer, Integer, String, JSON> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Integer, Integer, Integer, Integer, Integer, String, JSON> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return SiteUris.SITE_URIS.ID;
    }

    @Override
    public Field<Integer> field2() {
        return SiteUris.SITE_URIS.SITE_ID;
    }

    @Override
    public Field<Integer> field3() {
        return SiteUris.SITE_URIS.SITE_URI_ID;
    }

    @Override
    public Field<Integer> field4() {
        return SiteUris.SITE_URIS.LATEST_PARSER_VERSION_ID;
    }

    @Override
    public Field<Integer> field5() {
        return SiteUris.SITE_URIS.METHOD;
    }

    @Override
    public Field<String> field6() {
        return SiteUris.SITE_URIS.URI;
    }

    @Override
    public Field<JSON> field7() {
        return SiteUris.SITE_URIS.PARAMS;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getSiteId();
    }

    @Override
    public Integer component3() {
        return getSiteUriId();
    }

    @Override
    public Integer component4() {
        return getLatestParserVersionId();
    }

    @Override
    public Integer component5() {
        return getMethod();
    }

    @Override
    public String component6() {
        return getUri();
    }

    @Override
    public JSON component7() {
        return getParams();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getSiteId();
    }

    @Override
    public Integer value3() {
        return getSiteUriId();
    }

    @Override
    public Integer value4() {
        return getLatestParserVersionId();
    }

    @Override
    public Integer value5() {
        return getMethod();
    }

    @Override
    public String value6() {
        return getUri();
    }

    @Override
    public JSON value7() {
        return getParams();
    }

    @Override
    public SiteUrisRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public SiteUrisRecord value2(Integer value) {
        setSiteId(value);
        return this;
    }

    @Override
    public SiteUrisRecord value3(Integer value) {
        setSiteUriId(value);
        return this;
    }

    @Override
    public SiteUrisRecord value4(Integer value) {
        setLatestParserVersionId(value);
        return this;
    }

    @Override
    public SiteUrisRecord value5(Integer value) {
        setMethod(value);
        return this;
    }

    @Override
    public SiteUrisRecord value6(String value) {
        setUri(value);
        return this;
    }

    @Override
    public SiteUrisRecord value7(JSON value) {
        setParams(value);
        return this;
    }

    @Override
    public SiteUrisRecord values(Integer value1, Integer value2, Integer value3, Integer value4, Integer value5, String value6, JSON value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SiteUrisRecord
     */
    public SiteUrisRecord() {
        super(SiteUris.SITE_URIS);
    }

    /**
     * Create a detached, initialised SiteUrisRecord
     */
    public SiteUrisRecord(Integer id, Integer siteId, Integer siteUriId, Integer latestParserVersionId, Integer method, String uri, JSON params) {
        super(SiteUris.SITE_URIS);

        set(0, id);
        set(1, siteId);
        set(2, siteUriId);
        set(3, latestParserVersionId);
        set(4, method);
        set(5, uri);
        set(6, params);
    }
}
