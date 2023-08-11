package com.sumCo.modules.sys.service;

import java.util.Map;

import com.sumCo.modules.sys.entity.SysUserToken;

public interface SysUserTokenService {

	SysUserToken queryByUserId(Long userId);

	SysUserToken queryByToken(String token);
	
	void save(SysUserToken token);
	
	void update(SysUserToken token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	Map<String, Object> createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
