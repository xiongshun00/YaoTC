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
@TableName("charges_rules")
public class ChargesRules extends Model<ChargesRules> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 规则
     */
	@TableField("charges_rules")
	private String chargesRules;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChargesRules() {
		return chargesRules;
	}

	public void setChargesRules(String chargesRules) {
		this.chargesRules = chargesRules;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ChargesRules{" +
			"id=" + id +
			", chargesRules=" + chargesRules +
			"}";
	}
}
