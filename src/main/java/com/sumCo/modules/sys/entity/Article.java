package com.sumCo.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author oplus
 * @Description: TODO(文章)
 * @date 2017-11-30 15:35:54
 */
public class Article implements Serializable {
	
	//
	private Integer id;
	//標題
	private String title;
	//預覽圖
	private String image;
	//正文
	private String content;
	//創建時間
	private Date createTime;
	//最近一次修改時間
	private Date updateTime;

	/**
	 * 設置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 獲取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 設置：標題
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 獲取：標題
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 設置：預覽圖
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 獲取：預覽圖
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 設置：正文
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 獲取：正文
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 設置：創建時間
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 獲取：創建時間
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 設置：最近一次修改時間
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 獲取：最近一次修改時間
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

}
