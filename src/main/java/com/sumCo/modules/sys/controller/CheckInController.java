package com.sumCo.modules.sys.controller;

import com.sumCo.common.utils.PageUtils;
import com.sumCo.common.utils.Query;
import com.sumCo.common.utils.Result;
import com.sumCo.modules.sys.entity.CheckInVo;
import com.sumCo.modules.sys.entity.SysDept;
import com.sumCo.modules.sys.service.CheckInService;
import com.sumCo.modules.sys.service.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/sys/checkIn")
public class CheckInController extends AbstractController{

	@Autowired
	@Qualifier("CheckInServiceImpl")
	private CheckInService checkInService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:dept:list")
	public Result list(@RequestParam Map<String, Object> params){
		//查詢列表數據
//        Query query = new Query(params);
//
//		List<SysDept> sysDeptList = sysDeptService.queryList(query);
//		int total = sysDeptService.queryTotal(query);
//
//		PageUtils pageUtil = new PageUtils(sysDeptList, total, query.getLimit(), query.getPage());
		
		return Result.ok().put("page", null);
	}
	
	
	/**
	 * 資訊
	 */
	@RequestMapping("/info/{id}")
	public Result info(@PathVariable("id") Long id){
//		SysDept sysDept = sysDeptService.queryObject(id);
		SysDept sysDept = null;

		return Result.ok().put("sysDept", sysDept);
	}
	
	/**
	 * 儲存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:dept:save")
	public Result save(@RequestBody CheckInVo checkInVo){

		logger.info("getStatus=>"+checkInVo.getStatus());

		checkInService.checkIn(checkInVo);

		
		return Result.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:checkIn:update")
	public Result update(@RequestBody SysDept sysDept){
//		sysDeptService.update(sysDept);
		logger.info("123");
		return Result.ok();
	}
	
	/**
	 * 刪除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:dept:delete")
	public Result delete(@RequestBody Long[] ids){
//		sysDeptService.deleteBatch(ids);
		
		return Result.ok();
	}
	
}
