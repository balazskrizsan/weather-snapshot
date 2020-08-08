package com.kbalazsworks.weathersnapshot.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

public class SwaggerConfig
{
    public ApiInfo apiInfo()
    {
        return new ApiInfoBuilder()
            .title("Springfox petstore API")
            .description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum " +
                "has been the industry's standard dummy text ever since the 1500s, when an unknown printer "
                + "took a " +
                "galley of type and scrambled it to make a type specimen book. It has survived not only five " +
                "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
                "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum " +
                "passages, and more recently with desktop publishing software like Aldus PageMaker including " +
                "versions of Lorem Ipsum.")
            .termsOfServiceUrl("http://springfox.io")
            .contact(new Contact("springfox", "", ""))
            .license("Apache License Version 2.0")
            .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
            .version("2.0")
            .build();
    }
}
