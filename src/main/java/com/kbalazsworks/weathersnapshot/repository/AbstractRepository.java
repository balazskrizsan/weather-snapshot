package com.kbalazsworks.weathersnapshot.repository;

import com.kbalazsworks.weathersnapshot.utils.services.JooqService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractRepository
{
    private JooqService jooqService;

    @Autowired
    public void setJooqService(JooqService jooqService)
    {
        this.jooqService = jooqService;
    }

    DSLContext getQueryBuilder()
    {
        return jooqService.createQueryBuilder();
    }
}
