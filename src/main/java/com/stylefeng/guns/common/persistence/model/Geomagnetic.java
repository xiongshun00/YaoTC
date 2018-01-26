package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2017-12-19
 */
public class Geomagnetic extends Model<Geomagnetic> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 地磁编号
     */
	@TableField("geomagnetic_no")
	private String geomagneticNo;
    /**
     * 人为赋予唯一编号
     */
	@TableField("geomagnetic_num")
	private String geomagneticNum;
    


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGeomagneticNo() {
		return geomagneticNo;
	}

	public void setGeomagneticNo(String geomagneticNo) {
		this.geomagneticNo = geomagneticNo;
	}

	public String getGeomagneticNum() {
		return geomagneticNum;
	}

	public void setGeomagneticNum(String geomagneticNum) {
		this.geomagneticNum = geomagneticNum;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Geomagnetic{" +
			"id=" + id +
			", geomagneticNo=" + geomagneticNo +
			", geomagneticNum=" + geomagneticNum +
			"}";
	}
}
