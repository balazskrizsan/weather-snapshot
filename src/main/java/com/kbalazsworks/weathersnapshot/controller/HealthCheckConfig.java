package com.kbalazsworks.weathersnapshot.controller;

import com.kbalazsworks.weathersnapshot.config.SwaggerConfig;

public class HealthCheckConfig
{
    static final String CONTROLLER_URI = "/health-check";
    static final String GET_ACTION_ENV_VAR_TEST = "/env-var-test";

    SwaggerConfig swaggerConfig;

    public HealthCheckConfig()
    {
        this.swaggerConfig = new SwaggerConfig();
    }
}
