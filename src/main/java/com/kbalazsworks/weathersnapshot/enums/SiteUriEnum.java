package com.kbalazsworks.weathersnapshot.enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SiteUriEnum
{
    URI_ELOREJELZES(1),
    URI_30NAPOS(2),
    URI_IDOJARAS_ELOREJELZES(3);

    final private Integer name;
    private static final Map<Integer, SiteUriEnum> ENUM_MAP;

    SiteUriEnum(Integer name)
    {
        this.name = name;
    }

    public Integer getName()
    {
        return this.name;
    }

    static
    {
        Map<Integer, SiteUriEnum> map = new ConcurrentHashMap<>();
        for (SiteUriEnum instance : SiteUriEnum.values())
        {
            map.put(instance.getName(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static SiteUriEnum getByValue(Integer name)
    {
        return ENUM_MAP.get(name);
    }
}
