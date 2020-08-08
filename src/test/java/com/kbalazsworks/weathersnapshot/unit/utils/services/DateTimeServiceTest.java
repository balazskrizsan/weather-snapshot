package com.kbalazsworks.weathersnapshot.unit.utils.services;

import com.kbalazsworks.weathersnapshot.unit.AbstractUnitTest;
import com.kbalazsworks.weathersnapshot.utils.services.DateTimeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeServiceTest extends AbstractUnitTest
{
    @Autowired
    private DateTimeService dateTimeService;

    private class ConvertStringDateTimeToSqlTimestampTestClass
    {
        private String    testedTimestamp;
        private Timestamp expectedTimestamp;

        public ConvertStringDateTimeToSqlTimestampTestClass(String testedTimestamp, Timestamp expectedTimestamp)
        {
            this.testedTimestamp = testedTimestamp;
            this.expectedTimestamp = expectedTimestamp;
        }

        public String getTestedTimestamp()
        {
            return testedTimestamp;
        }

        public Timestamp getExpectedTimestamp()
        {
            return expectedTimestamp;
        }
    }

    private ConvertStringDateTimeToSqlTimestampTestClass
    provideConvertStringDateTimeToJavaTimestamp_prefect_perfect(int testCycle)
    {
        HashMap<Integer, ConvertStringDateTimeToSqlTimestampTestClass> map = new HashMap<>();

        map.put(
            1,
            new ConvertStringDateTimeToSqlTimestampTestClass("2018-02-03 14:15:16", new Timestamp(1517663716000L))
        );
        map.put(
            2,
            new ConvertStringDateTimeToSqlTimestampTestClass("1990-01-01 01:01:01", new Timestamp(631152061000L))
        );

        return map.get(testCycle);
    }

    private int i_convertStringDateTimeToSqlTimestamp_prefect_perfect;

    @Test
    @Repeat(2)
    public void convertStringDateTimeToSqlTimestamp_prefect_perfect() throws ParseException
    {
        int testCycle = ++i_convertStringDateTimeToSqlTimestamp_prefect_perfect;

        ConvertStringDateTimeToSqlTimestampTestClass testData =
            provideConvertStringDateTimeToJavaTimestamp_prefect_perfect(testCycle);

        // Arrange
        String    testedTimestamp   = testData.getTestedTimestamp();
        Timestamp expectedTimestamp = testData.getExpectedTimestamp();

        // Act
        Timestamp result = dateTimeService.convertStringDateTimeToSqlTimestamp(testedTimestamp);

        // Assert
        assertThat(result).isEqualTo(expectedTimestamp);
    }
}
