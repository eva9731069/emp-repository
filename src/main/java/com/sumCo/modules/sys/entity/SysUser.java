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

	private String name;

	private String roleName;



}
