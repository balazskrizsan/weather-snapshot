package com.kbalazsworks.weathersnapshot.exception;

public class ParserException extends Exception
{
    public ParserException(String errorMessage)
    {
        super(errorMessage);
    }

    public ParserException()
    {
    }
}
