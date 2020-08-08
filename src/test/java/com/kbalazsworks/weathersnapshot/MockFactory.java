package com.kbalazsworks.weathersnapshot;

import com.kbalazsworks.weathersnapshot.utils.factories.DateFactory;
import com.kbalazsworks.weathersnapshot.utils.factories.GregorianCalendarFactory;
import com.kbalazsworks.weathersnapshot.utils.factories.Slf4jLoggerFactory;
import com.kbalazsworks.weathersnapshot.utils.services.DateTimeService;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

public class MockFactory
{
    private static final DateTimeService dateTimeService = new DateTimeService();

    public static Date getMockJavaDateFromDate(String date) throws ParseException
    {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public static Date getMockJavaDateFromDateTime(String dateTime) throws ParseException
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
    }

    public static LocalDateTime getLocalDateTimeMockFromDateTime(String dateTime) throws ParseException
    {
        Date mockDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);

        return dateTimeService.convertJavaDateToLocalDateTime(mockDate);
    }

    public static DateFactory getMockDateFactory(Date date)
    {
        DateFactory mockDateFactory = Mockito.mock(DateFactory.class);
        Mockito.when(mockDateFactory.create()).thenReturn(date);

        return mockDateFactory;
    }

    public static DateFactory getMockDateFactoryFromDate(String date) throws ParseException
    {
        DateFactory mockDateFactory = Mockito.mock(DateFactory.class);
        Mockito.when(mockDateFactory.create()).thenReturn(getMockJavaDateFromDate(date));

        return mockDateFactory;
    }

    public static GregorianCalendarFactory mockGregorianCalendarFactory(Date date)
    {
        GregorianCalendarFactory mockGregorianCalendarFactory = Mockito.mock(GregorianCalendarFactory.class);
        Mockito
            .when(mockGregorianCalendarFactory.create())
            .thenAnswer(
                (Answer<GregorianCalendar>) invocation ->
                {
                    GregorianCalendar gregorianCalendar = new GregorianCalendar();
                    gregorianCalendar.setTime(date);

                    return gregorianCalendar;
                }
            );

        return mockGregorianCalendarFactory;
    }

    public static DateFactory getMockDateFactoryFromDateTime(String dateTime) throws ParseException
    {
        return getMockDateFactory(getMockJavaDateFromDateTime(dateTime));
    }

    public static Slf4jLoggerFactory getMockSlf4jLoggerFactory(Logger logger)
    {
        Slf4jLoggerFactory mockSlf4jLoggerFactory = Mockito.mock(Slf4jLoggerFactory.class);
        Mockito.when(mockSlf4jLoggerFactory.create(Mockito.any(Class.class))).thenReturn(logger);

        return mockSlf4jLoggerFactory;
    }
}
