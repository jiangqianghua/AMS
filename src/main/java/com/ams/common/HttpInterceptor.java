package com.ams.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ams.util.JsonMapper;

public class HttpInterceptor extends HandlerInterceptorAdapter{

	// 1 记录请求记录
	// 2 记录请求处理耗费的时间
	
	private static final String START_TIME = "requestStartTime";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI().toString();
		Map paramsMap = request.getParameterMap();
		logger.info("request start . url={}, params={}",url,JsonMapper.obj2String(paramsMap));
		
		long start = System.currentTimeMillis();
		request.setAttribute(START_TIME, start);
		return true ;
		
	}

	/**
	 * 正常情况下会调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String url = request.getRequestURI().toString();
		Map paramsMap = request.getParameterMap();
		long start = (Long) request.getAttribute(START_TIME);
		long end =System.currentTimeMillis();
		
		logger.info("request finished . url={}, params={},cost={}ms",url,JsonMapper.obj2String(paramsMap),end-start);
	}
	/**
	 * 任何情况都会调用，包括异常情况下
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String url = request.getRequestURI().toString();
		Map paramsMap = request.getParameterMap();
		long start = (Long) request.getAttribute(START_TIME);
		long end =System.currentTimeMillis();
		logger.info("request completed . url={}, params={}, cost={}ms",url,JsonMapper.obj2String(paramsMap),end-start);
	}

}
