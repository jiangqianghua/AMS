package com.ams.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 部门表单信息
 * @author jiangqianghua
 *
 */
public class DeptParam {

	private Integer id ;
	@NotBlank(message="部门名称不能为空")
	@Length(max=15 ,min=2,message="部门名称长度必须在2到15之间")
	private String name ;
	
	private Integer parentId = 0; 
	
	@NotNull(message="展示顺序不能为空")
	private Integer seq ; 
	
	@Length(	max=150,message="备注必须在150个字之内")
	private String remark ;

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
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
		this.remark = remark;
	}
	
	
	
}
