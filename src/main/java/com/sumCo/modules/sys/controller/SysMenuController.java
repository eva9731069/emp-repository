package com.sumCo.modules.sys.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumCo.common.Constant.MenuType;
import com.sumCo.common.annotation.SysLog;
import com.sumCo.common.exception.AppException;
import com.sumCo.common.utils.Result;
import com.sumCo.modules.sys.entity.SysMenu;
import com.sumCo.modules.sys.service.SysMenuService;
import com.sumCo.modules.sys.service.SysUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author oplus
 * @Description: TODO(系統菜單)
 * @date 2017-6-23 15:07
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 導航菜單
	 */
	@RequestMapping("/nav")
	public Result nav(){
		List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
		Set<String> permissions = sysUserService.getUserPermissions(getUserId());
		return Result.ok().put("menuList", menuList).put("permissions", permissions);
	}
	
	/**
	 * 所有菜單列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public List<SysMenu> list(){
		List<SysMenu> menuList = sysMenuService.queryList(new HashMap<String, Object>());

		return menuList;
	}
	
	/**
	 * 選擇菜單(添加、修改菜單)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public Result select(){
		//查詢列表數據
		List<SysMenu> menuList = sysMenuService.queryNotButtonList();
		
		//添加顶級菜單
		SysMenu root = new SysMenu();
		root.setId(0L);
		root.setName("一級菜單");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		
		return Result.ok().put("menuList", menuList);
	}
	
	/**
	 * 菜單信息
	 */
	@RequestMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public Result info(@PathVariable("menuId") Long menuId){
		SysMenu menu = sysMenuService.queryObject(menuId);
		return Result.ok().put("menu", menu);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存菜單")
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	public Result save(@RequestBody SysMenu menu){
		//數據校驗
		verifyForm(menu);
		
		sysMenuService.save(menu);
		
		return Result.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改菜單")
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:update")
	public Result update(@RequestBody SysMenu menu){
		//數據校驗
		verifyForm(menu);
				
		sysMenuService.update(menu);
		
		return Result.ok();
	}
	
	/**
	 * 刪除
	 */
	@SysLog("刪除菜單")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	public Result delete(long menuId){
		//判斷是否有子菜單或按鈕
		List<SysMenu> menuList = sysMenuService.queryListByParentId(menuId);
		if(menuList.size() > 0){
			return Result.error("請先刪除子菜單或按鈕");
		}

		sysMenuService.deleteBatch(new Long[]{menuId});
		
		return Result.ok();
	}
	
	/**
	 * 驗整參數是否正確
	 */
	private void verifyForm(SysMenu menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new AppException("菜單名稱不能為空");
		}
		
		if(menu.getParentId() == null){
			throw new AppException("上級菜單不能為空");
		}
		
		//菜單
		if(menu.getType() == MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new AppException("菜單URL不能為空");
			}
		}
		
		//上級菜單類型
		int parentType = MenuType.CATALOG.getValue();
		if(menu.getParentId() != 0){
			SysMenu parentMenu = sysMenuService.queryObject(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目錄、菜單
		if(menu.getType() == MenuType.CATALOG.getValue() ||
				menu.getType() == MenuType.MENU.getValue()){
			if(parentType != MenuType.CATALOG.getValue()){
				throw new AppException("上級菜單只能為目錄類型");
			}
			return ;
		}
		
		//按鈕
		if(menu.getType() == MenuType.BUTTON.getValue()){
			if(parentType != MenuType.MENU.getValue()){
				throw new AppException("上級菜单只能為菜單類型");
			}
			return ;
		}
	}
}
