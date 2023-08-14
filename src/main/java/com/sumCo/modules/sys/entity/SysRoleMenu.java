package com.sumCo.modules.sys.entity;

import java.io.Serializable;

/**
 * 角色與菜單對應關係
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:28:13
 */
public class SysRoleMenu implements Serializable {
	
	private Long id;

	/**
	 * 角色ID
	 */
	private Long roleId;


	private Long menuId;

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
	
	/**
	 * 設置：菜單ID
	 * @param menuId 菜單ID
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * 獲取：菜單ID
	 * @return Long
	 */
	public Long getMenuId() {
		return menuId;
	}
	
}
