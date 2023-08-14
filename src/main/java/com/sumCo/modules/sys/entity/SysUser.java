package com.sumCo.modules.sys.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.sumCo.common.validator.group.AddGroup;
import com.sumCo.common.validator.group.UpdateGroup;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author oplus
 * @Description: TODO(系統用戶)
 * @date 2017-6-23 15:07
 */
public class SysUser implements Serializable {
	
	/**
	 * 用戶ID
	 */
	private Long id;

	/**
	 * 用戶名
	 */
	@NotBlank(message="用戶名不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String username;

	/**
	 * 别名
	 */
	private String nickname;


	@NotBlank(message="密碼不能為空", groups = AddGroup.class)
	private String password;


	private String salt;


	//@NotBlank(message="郵箱不能為空", groups = {AddGroup.class, UpdateGroup.class})
	//@Email(message="郵箱格式不正確", groups = {AddGroup.class, UpdateGroup.class})
	private String email;


	//@NotBlank(message="手機號碼不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String mobile;


	private String avatar;


	private Integer status;
	

	private List<Long> roleIdList;

	private Long createUserId;


	private Date createTime;


	/**
	 * 設置：
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 獲取：
	 * @return Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 設置：用戶名
	 * @param username 用戶名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 獲取：用戶名
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 設置：密碼
	 * @param password 密碼
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 獲取：密碼
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 設置：郵箱
	 * @param email 郵箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 獲取：郵箱
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 設置：手機號碼
	 * @param mobile 手機號碼
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 獲取：手機號碼
	 * @return String
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 設置：狀態  0：禁用   1：正常
	 * @param status 狀態  0：禁用   1：正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 獲取：狀態  0：禁用   1：正常
	 * @return Integer
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 設置：創建時間
	 * @param createTime 創建時間
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 獲取：創建時間
	 * @return Date
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
}
