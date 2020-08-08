package com.kbalazsworks.weathersnapshot.enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SiteEnum
{
    IDOKEP(1),
    MET(2);

    final private Integer name;
    private static final Map<Integer, SiteEnum> ENUM_MAP;

    SiteEnum(Integer name)
    {
        this.name = name;
    }

    public Integer getName()
    {
        return this.name;
    }

    static
    {
        Map<Integer, SiteEnum> map = new ConcurrentHashMap<>();
        for (SiteEnum instance : SiteEnum.values())
        {
            map.put(instance.getName(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static SiteEnum getByValue(Integer name)
    {
        return ENUM_MAP.get(name);
    }
}
