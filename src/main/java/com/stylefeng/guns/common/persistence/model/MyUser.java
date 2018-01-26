package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2017-12-19
 */
@TableName("my_user")
public class MyUser extends Model<MyUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 账户
     */
	@TableField("user_account")
	private String userAccount;
    /**
     * 用户名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 密码
     */
	private String password;
    /**
     * 余额
     */
	private Double balance;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MyUser{" +
			"id=" + id +
			", userAccount=" + userAccount +
			", userName=" + userName +
			", password=" + password +
			", balance=" + balance +
			"}";
	}
}
