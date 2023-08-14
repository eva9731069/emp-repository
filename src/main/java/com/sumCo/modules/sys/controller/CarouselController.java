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
import com.sumCo.modules.sys.entity.Carousel;
import com.sumCo.modules.sys.service.CarouselService;

/**
 * @author oplus
 * @Description: TODO(輪播圖)
 * @date 2017-11-30 15:35:54
 */
@RestController
@RequestMapping("/sys/carousel")
public class CarouselController extends AbstractController{

	@Autowired
	private CarouselService carouselService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:carousel:list")
	public Result list(@RequestParam Map<String, Object> params){
		//查詢列表數據
        Query query = new Query(params);

		List<Carousel> carouselList = carouselService.queryList(query);
		int total = carouselService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(carouselList, total, query.getLimit(), query.getPage());
		
		return Result.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:carousel:info")
	public Result info(@PathVariable("id") Integer id){
		Carousel carousel = carouselService.queryObject(id);
		
		return Result.ok().put("carousel", carousel);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:carousel:save")
	public Result save(@RequestBody Carousel carousel){
		carouselService.save(carousel);
		
		return Result.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:carousel:update")
	public Result update(@RequestBody Carousel carousel){
		carouselService.update(carousel);
		
		return Result.ok();
	}
	
	/**
	 * 刪除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:carousel:delete")
	public Result delete(@RequestBody Integer[] ids){
		carouselService.deleteBatch(ids);
		
		return Result.ok();
	}
	
}
