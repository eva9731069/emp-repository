package com.sumCo.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sumCo.modules.sys.entity.SysUser;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserDao extends BaseDao<SysUser> {
	
	/**
	 * 查詢用戶的所有權限
	 * @param userId  用戶ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查詢用戶的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根據用戶名，查詢系統用戶
	 */
	SysUser queryByUserName(String username);
	
	/**
	 * 修改密碼
	 */
	int updatePassword(Map<String, Object> map);
	void upload(SysUser sysUser);
	int deleteBatchTwo(Object[]id);

	List<SysUser> queryJsReport();
}
