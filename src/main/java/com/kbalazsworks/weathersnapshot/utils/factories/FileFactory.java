package com.kbalazsworks.weathersnapshot.utils.factories;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileFactory
{
    public File create(String path)
    {
        return new File(path);
    }
}
