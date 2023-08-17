package com.sumCo.modules.sys.entity;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Data
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


	//	@NotBlank(message="郵箱不能為空", groups = {AddGroup.class, UpdateGroup.class})
//	@Email(message="郵箱格式不正確", groups = {AddGroup.class, UpdateGroup.class})
	private String email;


	//	@NotBlank(message="手機號碼不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String mobile;


	private String avatar;


	private Integer status;


	private List<Long> roleIdList;

	private Long createUserId;


	private Date createTime;
	private String empAccount;
	//	@NotBlank(message="英文名稱不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String enName;
	//	@NotBlank(message="身分證字號不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String personId;
	//	@NotBlank(message="性別不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String gender;
	//	@NotBlank(message="生日不能為空", groups = {AddGroup.class, UpdateGroup.class})
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birth;
	//	@NotBlank(message="年齡不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String age;
	//	@NotBlank(message="血型不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String bloodType;
	//	@NotBlank(message="身分類別不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String personType;
	//	@NotBlank(message="兵役狀況不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String soldierType;
	//	@NotBlank(message="婚姻狀態不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String marriedType;
	//	@NotBlank(message="戶籍地址不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String homeAddr;
	//	@NotBlank(message="聯絡地址不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String contactAddr;
	//	@NotBlank(message="住家電話不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String homePhone;
	//	@NotBlank(message="緊急聯絡人不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String pressingPerson;
	//	@NotBlank(message="緊急連絡人關係不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String pressingRelation;
	//	@NotBlank(message="緊急連絡電話不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String pressingPhone;
	//	@NotBlank(message="報到日期不能為空", groups = {AddGroup.class, UpdateGroup.class})
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date registerDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date resignDate;
	//	@NotBlank(message="大頭貼不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private byte[] empPhoto;
	private byte[] resignPhoto;
	//	@NotBlank(message="權限不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String role;
	//	@NotBlank(message="剩餘特休時數不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String yearHoliday;
	//	@NotBlank(message="底薪不能為空", groups = {AddGroup.class, UpdateGroup.class})
	private String basicSalary;


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

	public String getEmpAccount() {
		return empAccount;
	}

	public void setEmpAccount(String empAccount) {
		this.empAccount = empAccount;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getSoldierType() {
		return soldierType;
	}

	public void setSoldierType(String soldierType) {
		this.soldierType = soldierType;
	}

	public String getMarriedType() {
		return marriedType;
	}

	public void setMarriedType(String marriedType) {
		this.marriedType = marriedType;
	}

	public String getHomeAddr() {
		return homeAddr;
	}

	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	public String getContactAddr() {
		return contactAddr;
	}

	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getPressingPerson() {
		return pressingPerson;
	}

	public void setPressingPerson(String pressingPerson) {
		this.pressingPerson = pressingPerson;
	}

	public String getPressingRelation() {
		return pressingRelation;
	}

	public void setPressingRelation(String pressingRelation) {
		this.pressingRelation = pressingRelation;
	}

	public String getPressingPhone() {
		return pressingPhone;
	}

	public void setPressingPhone(String pressingPhone) {
		this.pressingPhone = pressingPhone;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getResignDate() {
		return resignDate;
	}

	public void setResignDate(Date resignDate) {
		this.resignDate = resignDate;
	}

	public byte[] getEmpPhoto() {
		return empPhoto;
	}

	public void setEmpPhoto(byte[] empPhoto) {
		this.empPhoto = empPhoto;
	}

	public byte[] getResignPhoto() {
		return resignPhoto;
	}

	public void setResignPhoto(byte[] resignPhoto) {
		this.resignPhoto = resignPhoto;
	}

	
	public String getYearHoliday() {
		return yearHoliday;
	}

	public void setYearHoliday(String yearHoliday) {
		this.yearHoliday = yearHoliday;
	}

	public String getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}
}
