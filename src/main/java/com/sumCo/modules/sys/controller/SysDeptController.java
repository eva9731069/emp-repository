package com.sumCo.modules.sys.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sumCo.modules.sys.entity.SysDept;
import com.sumCo.modules.sys.service.SysDeptService;
import com.sumCo.common.utils.PageUtils;
import com.sumCo.common.utils.Query;
import com.sumCo.common.utils.Result;

/**
 * @author Honda
 * @Description: TODO(部門管理)
 * @date 2023-08-08 11:01:45
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController{

	@Autowired
	private SysDeptService sysDeptService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:dept:list")
	public Result list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysDept> sysDeptList = sysDeptService.queryList(query);
		int total = sysDeptService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysDeptList, total, query.getLimit(), query.getPage());
		
		return Result.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 資訊
	 */
	@RequestMapping("/info/{id}")
	public Result info(@PathVariable("id") Long id){
		SysDept sysDept = sysDeptService.queryObject(id);
		
		return Result.ok().put("sysDept", sysDept);
	}
	
	/**
	 * 儲存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:dept:save")
	public Result save(@RequestBody SysDept sysDept){
		sysDept.setCreateTime(new Date());
		sysDeptService.save(sysDept);
		
		return Result.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:dept:update")
	public Result update(@RequestBody SysDept sysDept){
		sysDeptService.update(sysDept);
		
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:dept:delete")
	public Result delete(@RequestBody Long[] ids){
		sysDeptService.deleteBatch(ids);
		
		return Result.ok();
	}
	
}
