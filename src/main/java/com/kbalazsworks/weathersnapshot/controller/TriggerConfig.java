package com.kbalazsworks.weathersnapshot.controller;

import com.kbalazsworks.weathersnapshot.config.SwaggerConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TriggerConfig
{
    static final String CONTROLLER_URI = "/trigger";
    static final String GET_ACTION_DOWNLOAD = "/download";
    static final String GET_ACTION_START_PARSERS = "/start-parsers";

    SwaggerConfig swaggerConfig;

    public TriggerConfig()
    {
        this.swaggerConfig = new SwaggerConfig();
    }
}
