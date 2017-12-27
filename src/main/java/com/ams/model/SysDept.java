package com.ams.model;

import java.util.Date;


public class SysDept {
    private Integer id;

    private String name;

    private Integer parentId;

    private String level;

    private Integer seq;

    private String remark;

    private String operator;

    private Date operatorTime;

    private String operatorIp;

    
    
    public SysDept() {
		super();
	}

	public SysDept(Integer id, String name, Integer parentId, String level, Integer seq, String remark, String operator,
			Date operatorTime, String operatorIp) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.level = level;
		this.seq = seq;
		this.remark = remark;
		this.operator = operator;
		this.operatorTime = operatorTime;
		this.operatorIp = operatorIp;
	}

	public static SysDept builder(){
		return new SysDept() ;
	}
	
	public SysDept id(Integer id){
		this.id = id ; 
		return this ;
	}
	
	public SysDept name(String name){
		this.name = name ;
		return this ;
	}
	
	public SysDept parentId(Integer parentId){
		this.parentId = parentId;
		return this;
	}
	
	public SysDept seq(Integer seq){
		this.seq = seq ;
		return this;
	}
	
	public SysDept remark(String remark){
		this.remark = remark;
		return this;
	}
	
	public SysDept build(){
		return this ;
	}
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp == null ? null : operatorIp.trim();
    }
}