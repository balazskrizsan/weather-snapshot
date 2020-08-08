package com.kbalazsworks.weathersnapshot.service;

import com.kbalazsworks.weathersnapshot.entity.HtmlLog;
import com.kbalazsworks.weathersnapshot.repository.HtmlLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HtmlLogService
{
    private HtmlLogRepository htmlLogRepository;

    @Autowired
    public void setHtmlLogRepository(HtmlLogRepository htmlLogRepository)
    {
        this.htmlLogRepository = htmlLogRepository;
    }

    public void insert(HtmlLog htmlLog)
    {
        htmlLogRepository.insert(htmlLog);
    }

    public List<HtmlLog> searchNotParsed()
    {
        return htmlLogRepository.searchNotParsed();
    }

    public void markRecordParsed(Long id, Date now)
    {
        htmlLogRepository.markRecordParsed(id, now);
    }
}
