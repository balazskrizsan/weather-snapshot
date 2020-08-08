package com.kbalazsworks.weathersnapshot.enums;

import org.springframework.data.relational.core.sql.In;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum HttpMethodEnum
{
    GET(1),
    POST(2);

    final private Integer name;

    private static final Map<Integer, HttpMethodEnum> ENUM_MAP;

    HttpMethodEnum(Integer name)
    {
        this.name = name;
    }

    public Integer getName()
    {
        return this.name;
    }

    static
    {
        Map<Integer, HttpMethodEnum> map = new ConcurrentHashMap<>();
        for (HttpMethodEnum instance : HttpMethodEnum.values())
        {
            map.put(instance.getName(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static HttpMethodEnum getByValue(Integer name)
    {
        return ENUM_MAP.get(name);
    }
}
