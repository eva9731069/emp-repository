package com.sumCo.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author oplus
 * @Description: TODO(兵器)
 * @date 2017-12-19 13:59:34
 */
public class Generals implements Serializable {
	
	//
	private Integer id;
	//
	private String name;
	//
	private String image;
	//
	private String pcImage;
	//
	private String video;
	//
	private String previewImage;
	//
	private String previewVideo;
	//
	private String intro;
	//
	private Date createTime;
	//
	private Date updateTime;


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getId() {
		return id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getImage() {
		return image;
	}


	public void setPcImage(String pcImage) {
		this.pcImage = pcImage;
	}


	public String getPcImage() {
		return pcImage;
	}


	public void setVideo(String video) {
		this.video = video;
	}


	public String getVideo() {
		return video;
	}


	public void setPreviewImage(String previewImage) {
		this.previewImage = previewImage;
	}


	public String getPreviewImage() {
		return previewImage;
	}


	public void setPreviewVideo(String previewVideo) {
		this.previewVideo = previewVideo;
	}


	public String getPreviewVideo() {
		return previewVideo;
	}


	public void setIntro(String intro) {
		this.intro = intro;
	}


	public String getIntro() {
		return intro;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}

}
