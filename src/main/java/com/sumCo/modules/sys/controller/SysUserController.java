package com.sumCo.modules.sys.controller;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sumCo.common.annotation.SysLog;
import com.sumCo.common.exception.AppException;
import com.sumCo.common.utils.PageUtils;
import com.sumCo.common.utils.Query;
import com.sumCo.common.utils.Result;
import com.sumCo.common.validator.ValidatorUtils;
import com.sumCo.common.validator.group.AddGroup;
import com.sumCo.common.validator.group.UpdateGroup;
import com.sumCo.modules.sys.entity.SysUser;
import com.sumCo.modules.sys.service.SysUserRoleService;
import com.sumCo.modules.sys.service.SysUserService;

import java.util.List;
import java.util.Map;

/**
 * @author oplus
 * @Description: TODO(系统用户)
 * @date 2017-6-23 15:07
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public Result list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SysUser> userList = sysUserService.queryList(query);
		int total = sysUserService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
		
		return Result.ok().put("page", pageUtil);
	}
	
	/**
	 * 獲取登錄的使用者資訊
	 */
	@RequestMapping("/info")
	public Result info(){
		return Result.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密碼")
	@RequestMapping("/updatePassword")
	public Result updatePassword(String password, String newPassword){
		if(StringUtils.isBlank(newPassword)){
			throw new AppException("新密碼不可以無空值");
		}
		
		//sha256加密
		password = new Sha256Hash(password, getUser().getSalt()).toHex();
		//sha256加密
		newPassword = new Sha256Hash(newPassword, getUser().getSalt()).toHex();

		//更新密码
		int count = sysUserService.updatePassword(getUser(), password, newPassword);
		if(count == 0){
			return Result.error("密碼輸入不正確");
		}
		
		return Result.ok();
	}
	
	/**
	 * 使用者資訊
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public Result info(@PathVariable("userId") Long userId){
		SysUser user = sysUserService.queryObject(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return Result.ok().put("user", user);
	}
	
	/**
	 * 存 使用者
	 */
	@SysLog("儲存使用者")
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public Result save(@RequestBody SysUser user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		sysUserService.save(user);
		return Result.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public Result update(@RequestBody SysUser user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		sysUserService.update(user);
		return Result.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public Result delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return Result.error("系统管理員不能删除");
		}
		if(ArrayUtils.contains(userIds, getUserId())){
			return Result.error("此使用者不可以刪除");
		}
		sysUserService.deleteBatch(userIds);
		return Result.ok();
	}
}