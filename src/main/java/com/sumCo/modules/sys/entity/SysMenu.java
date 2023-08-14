package com.sumCo.modules.sys.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * @author oplus
 * @Description: TODO(菜單管理)
 * @date 2017-6-23 15:07
 */
public class SysMenu implements Serializable {
	
	/**
	 * 菜單ID
	 */
	private Long id;

	/**
	 * 父菜單ID，一級菜單為0
	 */
	private Long parentId;
	
	/**
	 * 父菜單名稱
	 */
	private String parentName;

	/**
	 * 菜單名稱
	 */
	@NotBlank(message="菜單名稱不能為空")
	private String name;

	/**
	 * 菜單URL
	 */
	@NotBlank(message="菜單URL")
	private String url;


	private String perms;


	private Integer type;


	private String icon;

	/**
	 * 排序
	 */
	private Integer orderNum;
	

	private Boolean open;
	
	private List<?> list;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	/**
	 * 設置：父菜單ID，一級菜單為0
	 * @param parentId 父菜單ID，一級菜單為0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 獲取：父菜單ID，一級菜單為0
	 * @return Long
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * 設置：菜單名稱
	 * @param name 菜單名稱
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 獲取：菜單名稱
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 設置：菜單URL
	 * @param url 菜單URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 獲取：菜單URL
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	
	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 設置：菜單圖標
	 * @param icon 菜單圖標
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 獲取：菜單圖標
	 * @return String
	 */
	public String getIcon() {
		return icon;
	}
	
	/**
	 * 設置：排序
	 * @param orderNum 排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 獲取：排序
	 * @return Integer
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
}
