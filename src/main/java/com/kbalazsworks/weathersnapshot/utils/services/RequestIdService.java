package com.kbalazsworks.weathersnapshot.utils.services;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestIdService
{
    private static String requestId = null;

    public static void generateNewRequestId()
    {
        requestId = UUID.randomUUID().toString();
    }

    public static String getRequestId()
    {
        if (null == requestId)
        {
            RequestIdService.generateNewRequestId();
        }

        return requestId;
    }
}
