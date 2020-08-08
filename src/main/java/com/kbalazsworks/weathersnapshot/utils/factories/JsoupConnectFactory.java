package com.kbalazsworks.weathersnapshot.utils.factories;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

@Component
public class JsoupConnectFactory
{
    public Connection create(String url)
    {
        return Jsoup.connect(url);
    }
}
