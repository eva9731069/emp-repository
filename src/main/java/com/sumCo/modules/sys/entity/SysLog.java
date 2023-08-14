package com.sumCo.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author oplus
 * @Description: TODO(系統日誌)
 * @date 2017-6-23 15:07
 */
public class SysLog implements Serializable {
	
	private Long id;
	//用戶名
	private String username;
	//用戶操作
	private String operation;
	//請求方法
	private String method;
	//請求參數
	private String params;
	//執行時長(毫秒)
	private Long time;
	//IP地址
	private String ip;
	//創建時間
	private Date createTime;


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getParams() {
		return params;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

}
