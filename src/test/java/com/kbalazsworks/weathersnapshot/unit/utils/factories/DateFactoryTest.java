package com.kbalazsworks.weathersnapshot.unit.utils.factories;

import com.kbalazsworks.weathersnapshot.unit.AbstractUnitTest;
import com.kbalazsworks.weathersnapshot.utils.factories.DateFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


public class DateFactoryTest extends AbstractUnitTest {
    @Autowired
    private DateFactory dateFactory;

    @Test
    public void create_perfect_perfect() {
        // Arrange

        // Act
        Date date = dateFactory.create();

        // Assert
        assertThat(date).isInstanceOf(Date.class);
    }
}

