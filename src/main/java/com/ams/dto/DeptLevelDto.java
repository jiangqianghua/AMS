package com.ams.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.ams.model.SysDept;
import com.google.common.collect.Lists;

public class DeptLevelDto extends SysDept{

	private List<DeptLevelDto> deptList = Lists.newArrayList();
	
	public static DeptLevelDto adapt(SysDept dept){
		DeptLevelDto dto = new DeptLevelDto();
		BeanUtils.copyProperties(dept, dto);
		return dto ;
	}

	public List<DeptLevelDto> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<DeptLevelDto> deptList) {
		this.deptList = deptList;
	}
	
	
}
