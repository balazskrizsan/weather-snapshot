package com.kbalazsworks.weathersnapshot.utils.builders;

import com.kbalazsworks.weathersnapshot.utils.services.RequestIdService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class ResponseEntityBuilder<T>
{
    private T           data;
    private int         errorCode  = 0;
    private HttpStatus  statusCode = HttpStatus.OK;
    private HttpHeaders headers    = new HttpHeaders();

    public ResponseEntity<ResponseData<T>> build() throws Exception
    {
        Boolean success = errorCode == 0;

        if (errorCode > 0 && statusCode == HttpStatus.OK)
        {
            throw new Exception("Status code setup is needed for error response");
        }

        ResponseData<T> responseData = new ResponseData<>(data, success, errorCode, RequestIdService.getRequestId());

        return new ResponseEntity<>(responseData, headers, statusCode);
    }

    public void downloadAsCsv(String fileName)
    {
        headers.setAccessControlExposeHeaders(Collections.singletonList("Content-Disposition"));
        headers.set("Content-Disposition", "attachment; filename=" + fileName + ".csv");
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    public HttpStatus getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode)
    {
        this.statusCode = statusCode;
    }
}
