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
@TableName("biz_complaint")
public class BizComplaintEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long complaintId;
	/**
	 *
	 */
	private String reporterName;
	/**
	 * 投诉人工作单位
	 */
	private String reporterCompany;
	/**
	 * 投诉人手机号
	 */
	private String mobile;
	/**
	 * 投诉内容
	 */
	private String content;
	/**
	 * 附件地址
	 */
	private String attachmentUrl;
	/**
	 *
	 */
	private Date gmtCreate;
	/**
	 *
	 */
	private Date gmtModified;

}
