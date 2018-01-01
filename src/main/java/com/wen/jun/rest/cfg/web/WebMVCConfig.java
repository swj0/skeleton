package com.wen.jun.rest.cfg.web;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wen.jun.common.JsonUtil;
import com.wen.jun.common.String2DateConverter;
import com.wen.jun.common.StringToEnumConverterFactoryWithNullAndTrim;
import com.wen.jun.common.StringToNumberConverterFactoryWithZero;

@Configuration
//@EnableWebMvc
public class WebMVCConfig extends WebMvcConfigurerAdapter{


	@Bean
    public Filter shallowEtagHeaderFilter() {
        HttpPutFormContentFilter filter = new HttpPutFormContentFilter();
        filter.setCharset(Charset.forName("UTF-8"));
        return filter;
    }
	
	@Bean
	public HandlerInterceptor commonHandlerInterceptor(){
		return new CommonHandlerInterceptor();
	}
	

    @Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(commonHandlerInterceptor()).addPathPatterns("/**");
	}
    

    @Override
         public void addResourceHandlers(ResourceHandlerRegistry registry) {
             registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
             registry.addResourceHandler("swagger-ui.html")
             .addResourceLocations("classpath:/META-INF/resources/");
             registry.addResourceHandler("/webjars/**")
             .addResourceLocations("classpath:/META-INF/resources/webjars/");
             super.addResourceHandlers(registry);
             
             System.out.println("skdfsdfsd...................................");
         }
	
	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		super.addArgumentResolvers(argumentResolvers);
		argumentResolvers.add(new RequestAttributeArgumentResolver());
		
	}
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToEnumConverterFactoryWithNullAndTrim());
        registry.addConverterFactory(new StringToNumberConverterFactoryWithZero());
        registry.addConverter(new String2DateConverter());
    }
	
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(new StringHttpMessageConverter(Charset.forName("utf-8")));
		converters.add(new MappingJackson2HttpMessageConverter(new JsonUtil.HJObjectMapperWithMybatisPage()));
		super.configureMessageConverters(converters);
		
		//converters.add(new MappingJackson2HttpMessageConverter(JsonUtil.objectMapperWithNull));
	}
	
	
	//@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(1024L * 1024L);
        return factory.createMultipartConfig();
    }
    
    
}
