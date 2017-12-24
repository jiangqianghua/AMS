package com.ams.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ams.common.JsonData;
import com.ams.exception.AmsException;
@Controller
@RequestMapping("/test")
public class TestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/hello1.json")
	@ResponseBody
	public JsonData hello(){
		logger.info("hello world log");
		throw new AmsException("异常啦！！！");
	//	return JsonData.success("json data...");
	}
	
}
