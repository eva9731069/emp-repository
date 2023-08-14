package com.sumCo.modules.sys.controller;

import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sumCo.common.utils.PageUtils;
import com.sumCo.common.utils.Query;
import com.sumCo.common.utils.Result;
import com.sumCo.modules.sys.entity.Generals;
import com.sumCo.modules.sys.service.GeneralsService;

/**
 * @author oplus
 * @Description: TODO(兵器)
 * @date 2017-12-19 13:59:34
 */
@RestController
@RequestMapping("/sys/generals")
public class GeneralsController extends AbstractController{

	@Autowired
	private GeneralsService generalsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:generals:list")
	public Result list(@RequestParam Map<String, Object> params){
		//查詢列表數據
        Query query = new Query(params);

		List<Generals> generalsList = generalsService.queryList(query);
		int total = generalsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(generalsList, total, query.getLimit(), query.getPage());
		
		return Result.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:generals:info")
	public Result info(@PathVariable("id") Integer id){
		Generals generals = generalsService.queryObject(id);
		
		return Result.ok().put("generals", generals);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:generals:save")
	public Result save(@RequestBody Generals generals){
		generalsService.save(generals);
		
		return Result.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:generals:update")
	public Result update(@RequestBody Generals generals){
		generalsService.update(generals);
		
		return Result.ok();
	}
	
	/**
	 * 刪除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:generals:delete")
	public Result delete(@RequestBody Integer[] ids){
		generalsService.deleteBatch(ids);
		
		return Result.ok();
	}
	
}
