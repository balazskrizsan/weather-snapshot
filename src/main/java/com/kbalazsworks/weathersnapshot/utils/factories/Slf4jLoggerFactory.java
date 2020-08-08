package com.kbalazsworks.weathersnapshot.utils.factories;

import com.kbalazsworks.weathersnapshot.config.ApplicationProperties;
import com.kbalazsworks.weathersnapshot.service.SearchBoxService;
import com.kbalazsworks.weathersnapshot.utils.services.LoggerDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Slf4jLoggerFactory
{
    private ApplicationProperties applicationProperties;
    private SearchBoxService searchBoxService;

    @Autowired
    public void setApplicationProperties(ApplicationProperties applicationProperties)
    {
        this.applicationProperties = applicationProperties;
    }

    @Autowired
    public void setSearchBoxService(SearchBoxService searchBoxService)
    {
        this.searchBoxService = searchBoxService;
    }

    public Logger create(Class logClass)
    {
        Logger logger = LoggerFactory.getLogger(logClass);

        return new LoggerDecorator(logger, applicationProperties, searchBoxService);
    }
}
