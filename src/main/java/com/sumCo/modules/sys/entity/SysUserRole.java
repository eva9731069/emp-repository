package com.sumCo.modules.sys.entity;

import java.io.Serializable;

/**
 * @author oplus
 * @Description: TODO(用戶與角色對應關係)
 * @date 2017-6-23 15:07
 */
public class SysUserRole implements Serializable {
	
	private Long id;


	private Long userId;

	/**
	 * 角色ID
	 */
	private Long roleId;

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
	 * 設置：用戶ID
	 * @param userId 用戶ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 獲取：用戶ID
	 * @return Long
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * 設置：角色ID
	 * @param roleId 角色ID
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * 獲取：角色ID
	 * @return Long
	 */
	public Long getRoleId() {
		return roleId;
	}
	
}
