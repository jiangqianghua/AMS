package com.ams.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ams.common.BeanValidator;
import com.ams.dao.SysDeptMapper;
import com.ams.exception.ParamsException;
import com.ams.model.SysDept;
import com.ams.param.DeptParam;
import com.ams.util.LevelUtil;
import com.google.common.base.Preconditions;

@Service
public class SysDeptService {

	@Resource
	private SysDeptMapper sysDeptMapper ;
	
	public void save(DeptParam param){
		BeanValidator.check(param);
		if(checkExist(param.getParentId(), param.getName(), param.getId())){
			throw new ParamsException("统一层级下存在相同的名称部门");
		}
		SysDept dept = SysDept.builder().name(param.getName()).parentId(param.getParentId())
				.seq(param.getSeq()).remark(param.getRemark()).build();
		dept.setLevel(LevelUtil.calcuateLeve(getLevel(param.getParentId()), param.getParentId()));
		dept.setOperator("system"); // TODO
		dept.setOperatorIp("127.0.0.1");
		dept.setOperatorTime(new Date());
		sysDeptMapper.insertSelective(dept);
	}
	
	private String getLevel(Integer deptId){
		SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
		if(dept == null)
			return null ;
		return dept.getLevel() ;
	}
	/**
	 * 更新部门信息
	 * @param param
	 */
	public void update(DeptParam param){
		BeanValidator.check(param);
		if(checkExist(param.getParentId(), param.getName(), param.getId())){
			throw new ParamsException("统一层级下存在相同的名称部门");
		}
		SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
		Preconditions.checkNotNull(before,"待更新部门不存在");
		
		SysDept after = SysDept.builder().id(param.getId()).name(param.getName()).parentId(param.getParentId())
				.seq(param.getSeq()).remark(param.getRemark()).build();
		after.setLevel(LevelUtil.calcuateLeve(getLevel(param.getParentId()), param.getParentId()));
		
		after.setOperator("system-update"); // TODO
		after.setOperatorIp("127.0.0.1");
		after.setOperatorTime(new Date());
		updateWhitChild(before,after);
		
	}
	
	@Transactional
	private void updateWhitChild(SysDept before , SysDept after){

		
		String newLevelPrefix = after.getLevel();
		String oldLevelPrefix = before.getLevel();
		if(!after.getLevel().equals(before.getLevel())){
			List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(before.getLevel());
			if(!CollectionUtils.isEmpty(deptList)){
				for(SysDept dept:deptList){
					String level = dept.getLevel();
					if(level.indexOf(oldLevelPrefix) == 0){
						level = newLevelPrefix+level.substring(oldLevelPrefix.length());
						dept.setLevel(level);
					}
				}
				sysDeptMapper.batchUpdateLevel(deptList);
			}
		}
		sysDeptMapper.updateByPrimaryKey(after);
	}
	
	/**
	 * 检测是否存在该部门
	 * @param parentId
	 * @param deptName
	 * @param deptId
	 * @return
	 */
	private boolean checkExist(Integer parentId,String deptName,Integer deptId){
		
		return sysDeptMapper.countByNameAndParentId(parentId, deptName, deptId) > 0;
	}
}
