/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.common.base.exception;

import com.alibaba.excel.exception.ExcelAnalysisException;
import com.xyz.common.base.ResponseVO;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public ResponseVO handleRRException(RRException e){
		ResponseVO r = new ResponseVO();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());

		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseVO handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return ResponseVO.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseVO handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return ResponseVO.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseVO handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return ResponseVO.error("没有权限，请联系管理员授权");
	}

    @ExceptionHandler(ExcelAnalysisException.class)
    public ResponseVO handleExcelAnalysisException(ExcelAnalysisException e){
        logger.error(e.getCause().getMessage(), e);
        return ResponseVO.error(e.getCause().getMessage());
    }

	@ExceptionHandler(Exception.class)
	public ResponseVO handleException(Exception e){
		logger.error(e.getMessage(), e);
		return ResponseVO.error();
	}
}
