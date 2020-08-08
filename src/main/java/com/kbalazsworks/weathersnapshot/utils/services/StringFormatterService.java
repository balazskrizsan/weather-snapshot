package com.kbalazsworks.weathersnapshot.utils.services;

public class StringFormatterService
{

    private Integer getIntegerValue(String value)
    {
        if (!value.trim().equals(""))
        {
            return Integer.valueOf(value.replaceAll(" ", ""));
        }

        return 0;
    }

    private Long getLongValue(String value)
    {
        if (!value.trim().equals(""))
        {
            return Long.parseLong(value.trim());
        }

        return 0L;
    }

    private Float getFloatValue(String value)
    {
        if (!value.trim().equals(""))
        {
            return Float.valueOf(value.replaceAll("[^0-9,\\.]", "").replace(",", "."));
        }

        return 0f;
    }
}
