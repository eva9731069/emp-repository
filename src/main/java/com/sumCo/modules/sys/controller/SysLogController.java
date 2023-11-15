package com.sumCo.modules.sys.controller;

import com.sumCo.modules.sys.service.NoSqlService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumCo.common.utils.PageUtils;
import com.sumCo.common.utils.Query;
import com.sumCo.common.utils.Result;
import com.sumCo.modules.sys.entity.SysLog;
import com.sumCo.modules.sys.service.SysLogService;

import java.util.List;
import java.util.Map;

/**
 * @author oplus
 * @Description: TODO(系統日誌)
 * @date 2017-6-23 15:07
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;

	@Autowired
	private NoSqlService noSqlService;

	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:log:list")
	public Result list(@RequestParam Map<String, Object> params){
		Query query = new Query(params);

		List<SysLog> sysLogList = null;

		//優先查詢noSql，增加查詢效能
		Document whereQ = new Document();
		whereQ.put("userName","admin");
		sysLogList = noSqlService.queryByUserName(whereQ, "sysLog");

		//noSql查詢不到改查db
		if(sysLogList.size() < 1){
			sysLogList = sysLogService.queryList(query);
		}

		int total = sysLogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysLogList, total, query.getLimit(), query.getPage());
		return Result.ok().put("page", pageUtil);
	}
	
}
