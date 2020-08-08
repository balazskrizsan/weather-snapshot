package com.kbalazsworks.weathersnapshot.utils.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbalazsworks.weathersnapshot.config.ApplicationProperties;
import com.kbalazsworks.weathersnapshot.service.SearchBoxService;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerDecorator implements Logger
{
    private final Logger logger;
    private final ApplicationProperties applicationProperties;
    private final SearchBoxService searchBoxService;

    public LoggerDecorator(Logger logger, ApplicationProperties applicationProperties, SearchBoxService searchBoxService)
    {
        this.logger = logger;
        this.applicationProperties = applicationProperties;
        this.searchBoxService = searchBoxService;
    }

    private void writeSearchBoxLog(String message, String level)
    {
        writeSearchBoxLog(message, level, "not added");
    }

    private void writeSearchBoxLog(String message, String level, Throwable throwable)
    {
        StringBuilder stackTrace = new StringBuilder();
        int i = 0;
        for (StackTraceElement element : throwable.getStackTrace())
        {
            i++;
            stackTrace.append(element.toString()).append("\n");
            if (i > 10)
            {
                break;
            }
        }

        writeSearchBoxLog(message, level, stackTrace.toString());
    }

    private void writeSearchBoxLog(String message, String level, String trace)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime rawNow = LocalDateTime.now();
        String now = formatter.format(rawNow);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode log = mapper
            .createObjectNode()
            .put("message", message)
            .put("level", level)
            .put("origin", logger.getName())
            .put("time", now)
            .put("environment", applicationProperties.getEnv())
            .put("trace", trace);

        searchBoxService.createDocument("log", log);
    }

    @Override
    public String getName()
    {
        return logger.getName();
    }

    @Override
    public boolean isTraceEnabled()
    {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg)
    {
        logger.trace(msg);
    }

    @Override
    public void trace(String format, Object arg)
    {
        logger.trace(format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2)
    {
        logger.trace(format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments)
    {
        logger.trace(format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t)
    {
        logger.trace(msg, t);
    }

    @Override
    public boolean isTraceEnabled(Marker marker)
    {
        return logger.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String msg)
    {
        logger.trace(marker, msg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg)
    {
        logger.trace(marker, format, arg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2)
    {
        logger.trace(marker, format, arg1, arg2);
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray)
    {
        logger.trace(marker, format, argArray);
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t)
    {
        logger.trace(marker, msg, t);
    }

    @Override
    public boolean isDebugEnabled()
    {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            writeSearchBoxLog(msg, "debug");

            return;
        }

        logger.debug(msg);
    }

    @Override
    public void debug(String format, Object arg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.debug:String:Object not implemented");

            return;
        }

        logger.debug(format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.debug not implemented");

            return;
        }

        logger.debug(format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.debug:String:Object... not implemented");

            return;
        }

        logger.debug(format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            writeSearchBoxLog(msg, "DEBUG", t);

            return;
        }

        logger.debug(msg, t);
    }

    @Override
    public boolean isDebugEnabled(Marker marker)
    {
        return logger.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String msg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.debug:Marker:String not implemented");

            return;
        }

        logger.debug(marker, msg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.debug:Marker:String:Object not implemented");

            return;
        }

        logger.debug(marker, format, arg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.debug:Marker:String:Object:Object not implemented");

            return;
        }

        logger.debug(marker, format, arg1, arg2);
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.debug:Marker:String:Object... not implemented");

            return;
        }

        logger.debug(marker, format, arguments);
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.debug:Marker:String:Throwable not implemented");

            return;
        }

        logger.debug(marker, msg, t);
    }

    @Override
    public boolean isInfoEnabled()
    {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String msg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            writeSearchBoxLog(msg, "INFO");

            return;
        }

        logger.info(msg);
    }

    @Override
    public void info(String format, Object arg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.info:String:Object not implemented");

            return;
        }

        logger.info(format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.info:String:Object:Object not implemented");

            return;
        }

        logger.info(format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.info:String:Object... not implemented");

            return;
        }

        logger.info(format, arguments);
    }

    @Override
    public void info(String msg, Throwable t)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.info:String:Throwable not implemented");

            return;
        }

        logger.info(msg, t);
    }

    @Override
    public boolean isInfoEnabled(Marker marker)
    {
        return logger.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String msg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.info:Marker:String not implemented");

            return;
        }

        logger.info(marker, msg);
    }

    @Override
    public void info(Marker marker, String format, Object arg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.info:Marker:String:Object not implemented");

            return;
        }

        logger.info(marker, format, arg);
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.info:Marker:String:Object:Object not implemented");

            return;
        }

        logger.info(marker, format, arg1, arg2);
    }

    @Override
    public void info(Marker marker, String format, Object... arguments)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.info:Marker:String:Object... not implemented");

            return;
        }

        logger.info(marker, format, arguments);
    }

    @Override
    public void info(Marker marker, String msg, Throwable t)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.info:Marker:String:Object not implemented");

            return;
        }

        logger.info(marker, msg, t);
    }

    @Override
    public boolean isWarnEnabled()
    {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            writeSearchBoxLog(msg, "WARN");

            return;
        }

        logger.warn(msg);
    }

    @Override
    public void warn(String format, Object arg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:String:Object not implemented");

            return;
        }

        logger.warn(format, arg);
    }

    @Override
    public void warn(String format, Object... arguments)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:String:Object... not implemented");

            return;
        }

        logger.warn(format, arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:String:Object:Object not implemented");

            return;
        }

        logger.warn(format, arg1, arg2);
    }

    @Override
    public void warn(String msg, Throwable t)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:String:Throwable not implemented");

            return;
        }

        logger.warn(msg, t);
    }

    @Override
    public boolean isWarnEnabled(Marker marker)
    {
        return logger.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String msg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:String:Throwable not implemented");

            return;
        }

        logger.warn(marker, msg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:Marker:String:Object not implemented");

            return;
        }

        logger.warn(marker, format, arg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:Marker:String:Object:Object not implemented");

            return;
        }

        logger.warn(marker, format, arg1, arg2);
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:Marker:String:Object... not implemented");

            return;
        }

        logger.error(marker, format, arguments);
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:Marker:String:Throwable not implemented");

            return;
        }

        logger.error(marker, msg, t);
    }

    @Override
    public boolean isErrorEnabled()
    {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String msg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            writeSearchBoxLog(msg, "ERROR");

            return;
        }

        logger.error(msg);
    }

    @Override
    public void error(String format, Object arg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:String:Object not implemented");

            return;
        }

        logger.error(format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:String:Object:Object not implemented");

            return;
        }

        logger.error(format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:String:Object... not implemented");

            return;
        }

        logger.error(format, arguments);
    }

    @Override
    public void error(String msg, Throwable t)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error(msg, "ERROR", t);

            return;
        }

        logger.error(msg, t);
    }

    @Override
    public boolean isErrorEnabled(Marker marker)
    {
        return logger.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String msg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:Marker:String not implemented");

            return;
        }

        logger.error(marker, msg);
    }

    @Override
    public void error(Marker marker, String format, Object arg)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:Marker:String:Object not implemented");

            return;
        }

        logger.error(marker, format, arg);
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:Marker:String:Object:Object not implemented");

            return;
        }

        logger.error(marker, format, arg1, arg2);
    }

    @Override
    public void error(Marker marker, String format, Object... arguments)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:Marker:String:Object... not implemented");

            return;
        }

        logger.error(marker, format, arguments);
    }

    @Override
    public void error(Marker marker, String msg, Throwable t)
    {
        if (applicationProperties.isSearchBoxLogEnabled())
        {
            error("LoggerDecorator.warn:Marker:String:Throwable not implemented");

            return;
        }

        logger.error(marker, msg, t);
    }
}
