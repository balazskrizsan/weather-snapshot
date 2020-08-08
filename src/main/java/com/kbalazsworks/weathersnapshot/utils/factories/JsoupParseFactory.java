package com.kbalazsworks.weathersnapshot.utils.factories;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class JsoupParseFactory
{
    public Document create(String html)
    {
        return Jsoup.parse(html);
    }
}
