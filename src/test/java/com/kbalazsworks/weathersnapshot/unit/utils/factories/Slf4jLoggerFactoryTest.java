package com.kbalazsworks.weathersnapshot.unit.utils.factories;

import com.kbalazsworks.weathersnapshot.unit.AbstractUnitTest;
import com.kbalazsworks.weathersnapshot.utils.factories.Slf4jLoggerFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class Slf4jLoggerFactoryTest extends AbstractUnitTest
{
    @Autowired
    Slf4jLoggerFactory slf4jLoggerFactory;

    @Test
    public void create_perfect_perfect()
    {
        // Arrange

        // Act
        Logger logger = slf4jLoggerFactory.create(Slf4jLoggerFactoryTest.class);

        // Assert
        assertThat(logger).isInstanceOf(Logger.class);
    }
}
