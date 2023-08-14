package com.sumCo.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sumCo.modules.sys.entity.SysMenu;

import java.util.List;

@Mapper
public interface SysMenuDao extends BaseDao<SysMenu> {
	
	/**
	 * 根據父菜單，查詢子菜單
	 * @param parentId 父菜單ID
	 */
	List<SysMenu> queryListByParentId(Long parentId);
	
	/**
	 * 獲取不包含按鈕的菜單列表
	 */
	List<SysMenu> queryNotButtonList();
	
	/**
	 * 查詢用戶的權限列表
	 */
	List<SysMenu> queryUserList(Long userId);
}
