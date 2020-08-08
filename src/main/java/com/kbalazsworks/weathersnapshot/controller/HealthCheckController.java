package com.kbalazsworks.weathersnapshot.controller;

import com.kbalazsworks.weathersnapshot.config.ApplicationProperties;
import com.kbalazsworks.weathersnapshot.utils.builders.ResponseData;
import com.kbalazsworks.weathersnapshot.utils.builders.ResponseEntityBuilder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableTransactionManagement
@RequestMapping(HealthCheckConfig.CONTROLLER_URI)
public class HealthCheckController
{
    ApplicationProperties applicationProperties;

    @Autowired
    public void setApplicationProperties(ApplicationProperties applicationProperties)
    {
        this.applicationProperties = applicationProperties;
    }

    @ApiOperation(
        value = "value"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ok"),
        @ApiResponse(code = 401, message = "not authorized!"),
        @ApiResponse(code = 403, message = "sala"),
        @ApiResponse(code = 404, message = "not found!!!")
    })
    @GetMapping(HealthCheckConfig.GET_ACTION_ENV_VAR_TEST)
    public ResponseEntity<ResponseData<String>> getEnvVarTest() throws Exception
    {
        ResponseEntityBuilder<String> responseEntityBuilder = new ResponseEntityBuilder<>();

        responseEntityBuilder.setData(applicationProperties.getEnvVarTest());

        return responseEntityBuilder.build();
    }
}
