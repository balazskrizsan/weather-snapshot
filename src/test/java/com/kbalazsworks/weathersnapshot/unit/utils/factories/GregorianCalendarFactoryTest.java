package com.kbalazsworks.weathersnapshot.unit.utils.factories;

import com.kbalazsworks.weathersnapshot.unit.AbstractUnitTest;
import com.kbalazsworks.weathersnapshot.utils.factories.GregorianCalendarFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;


public class GregorianCalendarFactoryTest extends AbstractUnitTest
{
    @Autowired
    GregorianCalendarFactory gregorianCalendarFactory;

    @Test
    public void create_perfect_perfect()
    {
        // Arrange

        // Act
        GregorianCalendar gregorianCalendar = gregorianCalendarFactory.create();

        // Assert
        assertThat(gregorianCalendar).isInstanceOf(GregorianCalendar.class);
    }
}
