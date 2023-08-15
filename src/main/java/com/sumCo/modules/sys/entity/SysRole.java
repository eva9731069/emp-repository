package com.sumCo.modules.sys.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author oplus
 * @Description: TODO(角色)
 * @date 2017-6-23 15:07
 */
public class SysRole implements Serializable {
	
	/**
	 * 角色ID
	 */
	private Long id;

	/**
	 * 角色名稱
	 */
	@NotBlank(message="群組名稱不得為空")
	private String name;

	/**
	 * 備註
	 */
	private String remark;
	

	private Long createUserId;
	
	private List<Long> menuIdList;
	

	private Date createTime;

	/**
	 * 設置：
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 獲取：
	 * @return Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 設置：角色名稱
	 * @param name 角色名稱
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 獲取：角色名稱
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 設置：備註
	 * @param remark 備註
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 獲取：備註
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}
	
}
