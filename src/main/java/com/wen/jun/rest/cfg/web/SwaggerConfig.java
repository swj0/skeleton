package com.wen.jun.rest.cfg.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("auth")
                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/auth/*"),regex("/auth/.*")))//过滤的接口
                .build()
                .apiInfo(testApiInfo());
    }
	
	private ApiInfo testApiInfo() {
        return new ApiInfoBuilder()
            .title("注册获取token")//大标题
            .description("注册获取token")//详细描述
            .version("1.0")//版本
            .termsOfServiceUrl("NO terms of service")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .build();
    }
	
	 @Bean
	    public Docket demoApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("users")
	                .genericModelSubstitutes(DeferredResult.class)
	              .genericModelSubstitutes(ResponseEntity.class)
	                .useDefaultResponseMessages(false)
	                .forCodeGeneration(false)
	                .pathMapping("/")
	                .select()
	                .paths(or(regex("/users/.*")))//过滤的接口
	                .build()
	                .apiInfo(demoApiInfo());
	    }
	 
	 private ApiInfo demoApiInfo() {
	        return new ApiInfoBuilder()
	            .title("用户api")//大标题
	            .description("操作用户资源api")//详细描述
	            .version("1.0")//版本
	            .termsOfServiceUrl("NO terms of service")
	            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	            .build();

	    }

	 
}
