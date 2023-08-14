package com.sumCo.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.sumCo.modules.sys.entity.SysConfig;

public interface SysConfigService {
	
	/**
	 * 保存配置信息
	 */
	public void save(SysConfig config);
	
	/**
	 * 更新配置信息
	 */
	public void update(SysConfig config);
	
	/**
	 * 刪除配置信息
	 */
	public void deleteBatch(Long[] ids);
	

	public List<SysConfig> queryList(Map<String, Object> map);

	public int queryTotal(Map<String, Object> map);
	
	public SysConfig queryObject(Long id);
	
	/**
	 * 根據key，獲取配置的value值
	 * 
	 * @param key	key
	 */
	public String getValue(String key);
	
	/**
	 * 根據key，獲取value的Object對象
	 * @param key	key
	 * @param clazz	Object對象
	 */
	public <T> T getConfigObject(String key, Class<T> clazz);

	SysConfig queryObjectByKey(String key);
	
}
