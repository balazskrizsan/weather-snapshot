package com.kbalazsworks.weathersnapshot.unit.utils.factories;

import com.kbalazsworks.weathersnapshot.unit.AbstractUnitTest;
import com.kbalazsworks.weathersnapshot.utils.factories.FileFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class FileFactoryTest extends AbstractUnitTest
{
    @Autowired
    FileFactory fileFactory;

    @Test
    public void create_perfect_perfect()
    {
        // Arrange
        String testFile = System.getProperty("user.dir") +
                          "/src/main/resources/test/unit/utils/factories/FileFactory/1.txt";

        // Act
        File file = fileFactory.create(testFile);

        // Assert
        assertAll(
            () -> assertThat(file).isInstanceOf(File.class),
            () -> assertThat(file.exists()).isTrue()
        );
    }
}
