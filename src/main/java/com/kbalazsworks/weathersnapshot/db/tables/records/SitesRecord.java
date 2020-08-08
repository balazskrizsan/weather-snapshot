/*
 * This file is generated by jOOQ.
 */
package com.kbalazsworks.weathersnapshot.db.tables.records;


import com.kbalazsworks.weathersnapshot.db.tables.Sites;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SitesRecord extends UpdatableRecordImpl<SitesRecord> implements Record3<Integer, String, String> {

    private static final long serialVersionUID = 1171923147;

    /**
     * Setter for <code>public.sites.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.sites.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.sites.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.sites.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.sites.domain</code>.
     */
    public void setDomain(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.sites.domain</code>.
     */
    public String getDomain() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Sites.SITES.ID;
    }

    @Override
    public Field<String> field2() {
        return Sites.SITES.NAME;
    }

    @Override
    public Field<String> field3() {
        return Sites.SITES.DOMAIN;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getDomain();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getDomain();
    }

    @Override
    public SitesRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public SitesRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public SitesRecord value3(String value) {
        setDomain(value);
        return this;
    }

    @Override
    public SitesRecord values(Integer value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SitesRecord
     */
    public SitesRecord() {
        super(Sites.SITES);
    }

    /**
     * Create a detached, initialised SitesRecord
     */
    public SitesRecord(Integer id, String name, String domain) {
        super(Sites.SITES);

        set(0, id);
        set(1, name);
        set(2, domain);
    }
}