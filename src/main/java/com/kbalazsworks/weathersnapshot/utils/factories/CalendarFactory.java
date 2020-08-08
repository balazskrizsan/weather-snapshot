package com.kbalazsworks.weathersnapshot.utils.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class CalendarFactory
{
    DateFactory dateFactory;

    @Autowired
    public void setDateFactory(DateFactory dateFactory)
    {
        this.dateFactory = dateFactory;
    }

    //@todo: test
    public Calendar create()
    {
        return Calendar.getInstance();
    }

    //@todo: test
    public Calendar create(boolean withDate)
    {
        Calendar calendar = Calendar.getInstance();
        if (withDate)
        {
            calendar.setTime(dateFactory.create());
        }

        return calendar;
    }

    //@todo: test
    public Calendar create(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }
}
