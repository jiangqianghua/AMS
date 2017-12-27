package com.ams.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ams.common.BeanValidator;
import com.ams.dao.SysDeptMapper;
import com.ams.dto.DeptLevelDto;
import com.ams.model.SysDept;
import com.ams.param.DeptParam;
import com.ams.util.LevelUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

@Service
public class SysTreeService {

	@Resource
	private SysDeptMapper sysDeptMapper ;
	/**
	 * 返回部门树
	 * @return
	 */
	public List<DeptLevelDto> deptTree(){
		List<SysDept> deptList = sysDeptMapper.getAllDept();
		List<DeptLevelDto> dtoList = Lists.newArrayList();
		for(SysDept dept:deptList){
			DeptLevelDto dto = DeptLevelDto.adapt(dept);
			dtoList.add(dto);
		}
		return deptListToTree(dtoList);
	}
	
	public List<DeptLevelDto> deptListToTree(List<DeptLevelDto> dtoList){
		if(CollectionUtils.isEmpty(dtoList)){
			return Lists.newArrayList();
		}
		
		Multimap<String,DeptLevelDto> levelDeptMap = ArrayListMultimap.create();
		List<DeptLevelDto> rootList = Lists.newArrayList();
		
		for(DeptLevelDto dto:dtoList){
			levelDeptMap.put(dto.getLevel(), dto);
			if(LevelUtil.ROOT.equals(dto.getLevel())){
				rootList.add(dto);
			}
		}
		// 按照seq排序从小到大排序
		Collections.sort(rootList,new Comparator<DeptLevelDto>() {

			public int compare(DeptLevelDto o1, DeptLevelDto o2) {
				return o1.getSeq() - o2.getSeq();
			}
		});
		transformDeptTree(rootList,LevelUtil.ROOT,levelDeptMap);
		return rootList ;
		
	}
	/**
	 * 递归生成树
	 * @param deptLevelList
	 * @param level
	 */
	public void transformDeptTree(List<DeptLevelDto> deptLevelList,String level,
			Multimap<String, DeptLevelDto> levelDeptMap){
		for(int 	i = 0 ; i < deptLevelList.size();i++){
			DeptLevelDto deptLevelDto = deptLevelList.get(i);
			String nextLevel = LevelUtil.calcuateLeve(level, deptLevelDto.getId());
			List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>)levelDeptMap.get(nextLevel);
			Collections.sort(tempDeptList,depSeqComparator);
			deptLevelDto.setDeptList(tempDeptList);
			transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
		}
	}
	
	public Comparator<DeptLevelDto> depSeqComparator = new Comparator<DeptLevelDto>() {

		public int compare(DeptLevelDto o1, DeptLevelDto o2) {
			return o1.getSeq() - o2.getSeq();
		}
		
	};
	
}
