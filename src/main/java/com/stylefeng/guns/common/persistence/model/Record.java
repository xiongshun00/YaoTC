package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2017-12-19
 */
public class Record extends Model<Record> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Integer userId;
	
	/**
	 * 用户姓名
	 */
	private String userName;
	
    /**
     * 地磁id
     */
	@TableField("geomagnetic_id")
	private Integer geomagneticId;
	
	/**
	 * 地磁别名
	 */
	private String geomagneticNum;
    /**
     * 开始时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 结束时间
     */
	@TableField("end_time")
	private Date endTime;
	/**
	 * 用时
	 */
	@TableField("times")
	private String times;
	/**
	 * 收费规则id
	 */
	@TableField("charges_rules_id")
	private int chargesRulesId;
	/**
	 * 应收金额
	 */
	@TableField("receivable")
	private double receivable;
	/**
	 * 状态
	 */
	@TableField("status")
	private int status;


	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public int getChargesRulesId() {
		return chargesRulesId;
	}

	public void setChargesRulesId(int chargesRulesId) {
		this.chargesRulesId = chargesRulesId;
	}

	public double getReceivable() {
		return receivable;
	}

	public void setReceivable(double receivable) {
		this.receivable = receivable;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGeomagneticId() {
		return geomagneticId;
	}

	public void setGeomagneticId(Integer geomagneticId) {
		this.geomagneticId = geomagneticId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGeomagneticNum() {
		return geomagneticNum;
	}

	public void setGeomagneticNum(String geomagneticNum) {
		this.geomagneticNum = geomagneticNum;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", userId=" + userId + ", userName="
				+ userName + ", geomagneticId=" + geomagneticId
				+ ", geomagneticNum=" + geomagneticNum + ", createTime="
				+ createTime + ", endTime=" + endTime + ", times=" + times
				+ ", chargesRulesId=" + chargesRulesId + ", receivable="
				+ receivable + ", status=" + status + "]";
	}



}
