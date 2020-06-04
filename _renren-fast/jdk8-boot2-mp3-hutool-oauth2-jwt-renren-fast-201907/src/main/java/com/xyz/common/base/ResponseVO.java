/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.common.base;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ResponseVO extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

//	private Integer code;
//	private String msg;

// @TODO @FIXME 前端对接结构目前不确定
// HashMap返回追加字段比较方便, 但很不规范!!
// private Object data;

	public ResponseVO() {
		put("code", 0);
		put("msg", "success");

//		setCode(0);
//		setMsg("success");
	}

	public static ResponseVO error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}

	public static ResponseVO error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}

	public static ResponseVO error(int code, String msg) {
		ResponseVO responseVO = new ResponseVO();

		responseVO.put("code", code);
		responseVO.put("msg", msg);

//		responseVO.setCode(code);
//		responseVO.setMsg(msg);
		return responseVO;
	}

	public static ResponseVO ok(String msg) {
		ResponseVO responseVO = new ResponseVO();
		responseVO.put("msg", msg);

//        responseVO.setMsg(msg);
		return responseVO;
	}

	public static ResponseVO ok(Map<String, Object> map) {
		ResponseVO r = new ResponseVO();
		r.putAll(map);
		return r;
	}

	public static ResponseVO ok() {
		return new ResponseVO();
	}

	public ResponseVO put(String key, Object value) {
		super.put(key, value);
		return this;
	}

//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
}
