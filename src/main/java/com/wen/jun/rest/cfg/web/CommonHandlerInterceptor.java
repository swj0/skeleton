package com.wen.jun.rest.cfg.web;

import static com.wen.jun.common.WebConsts.*;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;


public class CommonHandlerInterceptor implements HandlerInterceptor{
	private static Logger logger = LoggerFactory.getLogger(CommonHandlerInterceptor.class);
	//private ActiveMQService activeMQService = BeanRepository.getBean(ActiveMQService.class);
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object arg2, Exception ex)throws Exception {
		if(ex!=null){
			/*ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
			ex.printStackTrace(new java.io.PrintWriter(buf, true));
			String  expMessage = buf.toString();
			activeMQService.sendOSLogMsg("oslogs", null, LogLevel.Exception,LogType.CMS, expMessage, "");
			buf.close();
			//logger.error(expMessage);*/
		}
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		//response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,X_Requested_With");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		assemblePageBounds(request);
		return true;
	}
	
	//组装分页对象
	private void assemblePageBounds(HttpServletRequest request){
		String pageNumber = request.getParameter("page");
        String pageSizeStr = request.getParameter("limit");
        if(StringUtils.isNotBlank(pageNumber)||StringUtils.isNotBlank(pageSizeStr)){
        	int page = NumberUtils.toInt(pageNumber);
        	if(page<=0){
        		page=DEFAULT_FIRST_PAGE_NO;
        	}
        	int pageSize = NumberUtils.toInt(pageSizeStr);
        	if(pageSize<=0){
        		pageSize=DEFAULT_PAGE_SIZE;
        	}
        	PageBounds pageBounds = new PageBounds(page,pageSize);
        	request.setAttribute(PGK, pageBounds);
        }
	}

}












