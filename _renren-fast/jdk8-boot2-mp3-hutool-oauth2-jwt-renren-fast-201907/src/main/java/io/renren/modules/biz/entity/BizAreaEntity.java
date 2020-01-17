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
@TableName("biz_area")
public class BizAreaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 城市ID
	 */
	@TableId
	private Long areaId;
	/**
	 * 地区名称
	 */
	private String name;
	/**
	 * 父级地区id
	 */
	private Long parentId;
	/**
	 * 通用编码
	 */
	private String code;
	/**
	 * 自定义编码
	 */
	private String customCode;
	/**
	 * 是否删除(0 否; 1: 是)
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
