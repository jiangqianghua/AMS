package com.ams.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ams.common.ApplicationContextHelper;
import com.ams.common.BeanValidator;
import com.ams.common.JsonData;
import com.ams.dao.SysAclModuleMapper;
import com.ams.exception.AmsException;
import com.ams.exception.ParamsException;
import com.ams.model.SysAclModule;
import com.ams.param.TestVo;
import com.ams.util.JsonMapper;
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
	
	
	@RequestMapping("/validate.json")
	@ResponseBody
	public JsonData validateData(TestVo vo) throws ParamsException{
		logger.info("validateData");
		BeanValidator.check(vo);
		return JsonData.success("json data...");
	}
	
	@RequestMapping("/jackson.json")
	@ResponseBody
	public JsonData jacksonData(TestVo vo) throws ParamsException{
		logger.info("jacksonData");
		SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
		SysAclModule module = moduleMapper.selectByPrimaryKey(1);
		logger.info(JsonMapper.obj2String(module));
		BeanValidator.check(vo);
		return JsonData.success("json data...");
	}
	
	
}
