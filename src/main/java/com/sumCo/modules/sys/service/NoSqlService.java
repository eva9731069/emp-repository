package com.sumCo.modules.sys.service;

import com.sumCo.modules.sys.entity.SysLog;
import org.bson.Document;
import java.util.List;

public interface NoSqlService {

	/**
	 *
	 * @param whereDocKey 查詢條件
	 * @param collectionName 查詢的document名稱
	 * @return
	 */
	List<SysLog> queryByUserName(Document whereDocKey, String collectionName);


	/**
	 *
	 * @param sysLog
	 * @param collectionName 新增的document名稱
	 * @param document 新增的資料
	 */
	void insert(SysLog sysLog, String collectionName, Document document);
	

}
