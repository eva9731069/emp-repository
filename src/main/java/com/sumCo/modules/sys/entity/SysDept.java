package com.sumCo.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Honda
 * @Description: TODO(部門管理)
 * @date 2023-08-08 11:01:45
 */
public class SysDept implements Serializable {
	
	//
	private Long id;
	//部門名稱
	private String name;
	//上級部門編號
	private String parentId;
	//排序
	private String orderNum;
	//狀態  0：停用   1：正常
	private Integer status;
	//建立時間
	private Date createTime;


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

}
