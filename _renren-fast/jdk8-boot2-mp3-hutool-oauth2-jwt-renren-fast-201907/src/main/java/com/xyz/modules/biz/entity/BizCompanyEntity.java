package com.xyz.modules.biz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-23 20:00:17
 */
@Data
@TableName("biz_company")
public class BizCompanyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 公司ID
	 */
	@TableId
	private Long companyId;
	/**
	 *
	 */
	private String companyName;
	/**
	 * 公司所在地区
	 */
	private Long areaId;
	/**
	 * 签约人
	 */
	private String signPerson;
	/**
	 * 签约时间
	 */
	private Date signDate;
	/**
	 *
	 */
	private Date gmtCreate;
	/**
	 *
	 */
	private Date gmtModified;

}
