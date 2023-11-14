package com.sumCo.modules.sys.service.impl;

import com.sumCo.modules.sys.dao.SysRoleDao;
import com.sumCo.modules.sys.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumCo.modules.sys.dao.SysUserRoleDao;
import com.sumCo.modules.sys.service.SysUserRoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	@Transactional
	public void saveOrUpdate(Long userId, List<Long> roleIdList, String roleName) {
		this.delete(userId);

		SysRole sysRole = sysRoleDao.queryByRoleName(roleName);

		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("roleIdList", roleIdList);
		map.put("roleId", sysRole.getId());
		sysUserRoleDao.save(map);
	}

	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return sysUserRoleDao.queryRoleIdList(userId);
	}

	@Override
	@Transactional
	public void delete(Long userId) {
		sysUserRoleDao.delete(userId);
	}

	@Override
	public void deleteBatch(Long[] userIds) {
		sysUserRoleDao.deleteBatch(userIds);
	}
}
