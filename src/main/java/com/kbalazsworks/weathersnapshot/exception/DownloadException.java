package com.kbalazsworks.weathersnapshot.exception;

public class DownloadException extends Exception
{
    public DownloadException(String errorMessage)
    {
        super(errorMessage);
    }

    public DownloadException()
    {
    }
}
