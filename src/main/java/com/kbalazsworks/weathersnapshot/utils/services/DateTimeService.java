package com.kbalazsworks.weathersnapshot.utils.services;

import com.kbalazsworks.weathersnapshot.utils.factories.GregorianCalendarFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class DateTimeService
{
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private GregorianCalendarFactory gregorianCalendarFactory;

    @Autowired
    public void setGregorianCalendarFactory(GregorianCalendarFactory gregorianCalendarFactory)
    {
        this.gregorianCalendarFactory = gregorianCalendarFactory;
    }

    public java.sql.Date convertJavaDateToSqlDate(Date date) // @todo: test
    {
        return new java.sql.Date(date.getTime());
    }

    public Timestamp convertJavaDateToSqlTimestamp(Date date) // @todo: test
    {
        return new Timestamp(date.getTime());
    }

    public Timestamp convertStringDateTimeToSqlTimestamp(String date) throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return new Timestamp(simpleDateFormat.parse(date).getTime());
    }

    public Date convertStringDateToJavaDate(String date) // @todo: test
    {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            return simpleDateFormat.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public Date getJavaDateBackByDay(int days) //@todo: test
    {
        GregorianCalendar gregorianCalendar = gregorianCalendarFactory.create();
        gregorianCalendar.add(Calendar.DATE, days);

        return gregorianCalendar.getTime();
    }

    public String getStringDateFromJavaDate(Date date) //@todo: test
    {
        return dateFormat.format(date);
    }

    public Timestamp convertSqlDateTimeToSqlTimestamp(Date date) //@todo: test
    {
        return new Timestamp(date.getTime());
    }

    public String convertSqlTimestampToDateString(Timestamp lastSuccessParseDate) //@todo: test
    {
        return getStringDateFromJavaDate(new Date(lastSuccessParseDate.getTime()));
    }

    public LocalDateTime convertJavaDateToLocalDateTime(java.util.Date date)
    {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public Date convertLocalDateTimeToJavaDate(LocalDateTime localDateTime)
    {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
