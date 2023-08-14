package com.sumCo.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.sumCo.modules.sys.entity.Carousel;

/**
 * @author oplus
 * @Description: TODO(輪播圖)
 * @date 2017-11-30 15:35:54
 */
public interface CarouselService {
	
	Carousel queryObject(Integer id);
	
	List<Carousel> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(Carousel carousel);
	
	void update(Carousel carousel);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

}
