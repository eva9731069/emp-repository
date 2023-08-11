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
	//上级部門編號
	private String parentId;
	//排序
	private String orderNum;
	//状态  0：停用   1：正常
	private Integer status;
	//建立時間
	private Date createTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：部門名稱
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：部門名稱
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：上级部門編號
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：上级部門編號
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：排序
	 */
	public String getOrderNum() {
		return orderNum;
	}
	/**
	 * 设置：状态  0：停用   1：正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态  0：停用   1：正常
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：建立時間
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：建立時間
	 */
	public Date getCreateTime() {
		return createTime;
	}

}
