package com.ams.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ams.common.JsonData;
import com.ams.dto.DeptLevelDto;
import com.ams.param.DeptParam;
import com.ams.service.SysDeptService;
import com.ams.service.SysTreeService;

@Controller
@RequestMapping("sys/dept")
public class SysDeptController {

	@Resource
	private SysDeptService sysDeptService;
	@Resource
	private SysTreeService sysTreeService;
	
	@RequestMapping("/dept.page")
	public ModelAndView page(){
		return new ModelAndView("dept");
	}
	@RequestMapping("/save.json")
	@ResponseBody
	public JsonData saveDept(DeptParam param){
		sysDeptService.save(param);
		return JsonData.success();
	}
	
	@RequestMapping("/tree.json")
	@ResponseBody
	public JsonData tree(){
		List<DeptLevelDto> dtoList = sysTreeService.deptTree();
		return JsonData.success(dtoList);
	}
	
	@RequestMapping("/update.json")
	@ResponseBody
	public JsonData update(DeptParam param){
		sysDeptService.update(param);
		return JsonData.success();
	}
	
	
	
}
