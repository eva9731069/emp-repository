package com.sumCo.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author oplus
 * @Description: TODO(輪播圖)
 * @date 2017-11-30 15:35:54
 */
public class Carousel implements Serializable {
	
	//
	private Integer id;
	//
	private String title;
	//
	private String image;
	//pc端圖片
	private String pcImage;
	//0:圖片1：影片2：問卷3：連結4:測試電腦配置
	private Integer type;
	//
	private String content;
	//
	private Date createTime;
	//
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
	 * 設置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 獲取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 設置：
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 獲取：
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 設置：pc端圖片
	 */
	public void setPcImage(String pcImage) {
		this.pcImage = pcImage;
	}
	/**
	 * 獲取：pc端圖片
	 */
	public String getPcImage() {
		return pcImage;
	}
	/**
	 * 設置：0:圖片1：影片2：問卷3：連結4:測試電腦配置
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 獲取：0:圖片1：影片2：問卷3：連結4:測試電腦配置
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 設置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 獲取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 設置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 獲取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 設置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 獲取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

}
