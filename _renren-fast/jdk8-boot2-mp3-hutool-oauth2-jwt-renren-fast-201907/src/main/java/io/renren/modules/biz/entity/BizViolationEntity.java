package io.renren.modules.biz.entity;

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
@TableName("biz_violation")
public class BizViolationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long violationId;
	/**
	 * 处理办法
	 */
	private String treatMethod;
	/**
	 * 违约情况描述
	 */
	private String description;
	/**
	 * 违约日期
	 */
	private Date violationDate;
	/**
	 * 是否显示到网站(0: 否; 1: 是 )
	 */
	private Integer isShow;
	/**
	 * 是否删除(0: 否；1: 是)
	 */
	private Integer isDelete;
	/**
	 *
	 */
	private Date gmtCreate;
	/**
	 *
	 */
	private Date gmtModified;

}
