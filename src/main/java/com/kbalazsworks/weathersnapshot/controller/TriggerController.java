package com.kbalazsworks.weathersnapshot.controller;

import com.kbalazsworks.weathersnapshot.service.DownloaderService;
import com.kbalazsworks.weathersnapshot.service.ParserService;
import com.kbalazsworks.weathersnapshot.utils.builders.ResponseData;
import com.kbalazsworks.weathersnapshot.utils.builders.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableTransactionManagement
@RequestMapping(TriggerConfig.CONTROLLER_URI)
public class TriggerController
{
    private DownloaderService downloadService;
    private ParserService parserService;

    @Autowired
    public void setDownloadService(DownloaderService downloadService)
    {
        this.downloadService = downloadService;
    }

    @Autowired
    public void setParserService(ParserService parserService)
    {
        this.parserService = parserService;
    }

    @GetMapping(TriggerConfig.GET_ACTION_DOWNLOAD)
    public ResponseEntity<ResponseData<Void>> getDownload() throws Exception
    {
        downloadService.startDownload();

        ResponseEntityBuilder<Void> responseEntityBuilder = new ResponseEntityBuilder<>();
        responseEntityBuilder.setStatusCode(HttpStatus.NO_CONTENT);

        return responseEntityBuilder.build();
    }

    @GetMapping(TriggerConfig.GET_ACTION_START_PARSERS)
    public ResponseEntity<ResponseData<Void>> getStartParsers() throws Exception
    {
        parserService.start();

        ResponseEntityBuilder<Void> responseEntityBuilder = new ResponseEntityBuilder<>();
        responseEntityBuilder.setStatusCode(HttpStatus.NO_CONTENT);

        return responseEntityBuilder.build();
    }
}
