package com.ams.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ams.model.SysDept;

public interface SysDeptMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);
    
    List<SysDept> getAllDept();
    
    List<SysDept> getChildDeptListByLevel(@Param("level")String level);
    
    void batchUpdateLevel(@Param("sysDeptList")List<SysDept> sysDeptList);
    
    int countByNameAndParentId(@Param("parentId") Integer parentId,@Param("name")String name,@Param("id") Integer id);
}