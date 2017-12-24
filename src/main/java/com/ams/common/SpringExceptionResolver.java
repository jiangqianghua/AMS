package com.ams.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ams.exception.AmsException;
import com.ams.exception.ParamsException;
/**
 * 拦截全局的异常
 * @author jiangqianghua
 *
 */
public class SpringExceptionResolver implements HandlerExceptionResolver {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse respon, Object obj,
			Exception exception) {
		
		String url =request.getRequestURL().toString();
		ModelAndView mv ; 
		String defaultMsg = "System error";
		// 访问url都是.json结尾
		if(url.endsWith(".json")){
			if(exception instanceof AmsException || exception instanceof ParamsException){
				logger.error("unkown json exception ,url="+url,exception);
				JsonData result = JsonData.fail(exception.getMessage());
				// jsonView 是spring-servlet.xml配置的bean的id
				mv = new ModelAndView("jsonView",result.toMap());
			}else{
				logger.error("unkown json exception ,url="+url,exception);
				logger.error("unkown exception ,url="+url,exception);
				JsonData result = JsonData.fail(defaultMsg);
				mv = new ModelAndView("jsonView",result.toMap());	
			}
		}else if(url.endsWith(".page")){
			//访问url都是.page结尾
			logger.error("unkown page exception ,url="+url,exception);
			JsonData result = JsonData.fail(defaultMsg);
			mv = new ModelAndView("exception",result.toMap());
		}else{
			logger.error("unkown exception ,url="+url,exception);
			JsonData result = JsonData.fail(defaultMsg);
			mv = new ModelAndView("jsonView",result.toMap());
		}
		return mv;
	}

}
