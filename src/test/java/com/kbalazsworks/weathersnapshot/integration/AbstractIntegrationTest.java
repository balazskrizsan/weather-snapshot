package com.kbalazsworks.weathersnapshot.integration;

import com.kbalazsworks.weathersnapshot.AbstractTest;
import com.kbalazsworks.weathersnapshot.utils.services.JooqService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

public abstract class AbstractIntegrationTest extends AbstractTest
{
//    @LocalServerPort
//    private int         port;
    private JooqService jooqService;

//    int getPort()
//    {
//        return port;
//    }

    @Autowired
    public void setJooqService(JooqService jooqService)
    {
        this.jooqService = jooqService;
    }

    protected DSLContext getQueryBuilder()
    {
        return jooqService.createQueryBuilder();
    }
}
