package com.wen.jun.rest.cfg.web;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import static com.wen.jun.common.WebConsts.*;

//处理 @RequestAttribute
public class RequestAttributeArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter arg0,
			ModelAndViewContainer arg1, NativeWebRequest webRequest,
			WebDataBinderFactory arg3) throws Exception {
		RequestAttribute attr = arg0.getParameterAnnotation(RequestAttribute.class);
		String value = attr.value();
		Object attribute = webRequest.getAttribute(value, WebRequest.SCOPE_REQUEST);
		if (PGK.equals(value) && attribute == null) {
            attribute = new PageBounds(DEFAULT_FIRST_PAGE_NO, DEFAULT_PAGE_SIZE);
            webRequest.setAttribute(value, attribute, WebRequest.SCOPE_REQUEST);
        }
		return attribute;
	}

	@Override
	public boolean supportsParameter(MethodParameter arg0) {
		boolean f = arg0.getParameterAnnotation(RequestAttribute.class)!=null;
		return f;
	}
	
}
