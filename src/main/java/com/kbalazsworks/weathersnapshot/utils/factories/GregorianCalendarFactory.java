package com.kbalazsworks.weathersnapshot.utils.factories;

import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;

@Component
public class GregorianCalendarFactory
{
    public GregorianCalendar create()
    {
        return new GregorianCalendar();
    }
}
