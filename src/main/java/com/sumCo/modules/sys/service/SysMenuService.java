package com.sumCo.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.sumCo.modules.sys.entity.SysMenu;

public interface SysMenuService {
	
	/**
	 * 根據父菜單，查詢子菜單
	 * @param parentId 父菜單ID
	 * @param menuIdList  用戶菜單ID
	 */
	List<SysMenu> queryListByParentId(Long parentId, List<Long> menuIdList);

	/**
	 * 根據父菜單，查詢子菜單
	 * @param parentId 父菜單ID
	 */
	List<SysMenu> queryListByParentId(Long parentId);
	

	List<SysMenu> queryNotButtonList();
	

	List<SysMenu> getUserMenuList(Long userId);
	

	SysMenu queryObject(Long id);
	

	List<SysMenu> queryList(Map<String, Object> map);
	

	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存菜單
	 */
	void save(SysMenu menu);
	
	/**
	 * 修改
	 */
	void update(SysMenu menu);
	
	/**
	 * 刪除
	 */
	void deleteBatch(Long[] ids);
	

	List<SysMenu> queryUserList(Long userId);
}
