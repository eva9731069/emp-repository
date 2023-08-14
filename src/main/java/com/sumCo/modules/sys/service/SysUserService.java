package com.sumCo.modules.sys.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sumCo.modules.sys.entity.SysUser;

public interface SysUserService {

	/**
	 *
	 * 查詢用戶的所有權限
	 * @param userId  用戶ID
	 */
	List<String> queryAllPerms(Long userId);

	/**
	 *
	 * 查詢用戶的所有菜單ID
	 */
	List<Long> queryAllMenuId(Long userId);

	/**
	 *
	 * 根據用戶名，查詢系統用戶
	 */
	SysUser queryByUserName(String username);

	/**
	 *
	 * 根據用戶ID，查詢用戶
	 * @param id
	 * @return
	 */
	SysUser queryObject(Long id);


	List<SysUser> queryList(Map<String, Object> map);


	int queryTotal(Map<String, Object> map);



	void save(SysUser user);



	void update(SysUser user);



	void deleteBatch(Long[] ids);

	/**
	 *
	 * 修改密碼
	 * @param user       用戶
	 * @param password     原密碼
	 * @param newPassword  新密碼
	 */
	int updatePassword(SysUser user, String password, String newPassword);



	Set<String> getUserPermissions(Long userId);

}