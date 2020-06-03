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

	public ResponseVO() {
		put("code", 0);
		put("msg", "success");
	}

	public static ResponseVO error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}

	public static ResponseVO error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}

	public static ResponseVO error(int code, String msg) {
		ResponseVO r = new ResponseVO();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static ResponseVO ok(String msg) {
		ResponseVO r = new ResponseVO();
		r.put("msg", msg);
		return r;
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
}
