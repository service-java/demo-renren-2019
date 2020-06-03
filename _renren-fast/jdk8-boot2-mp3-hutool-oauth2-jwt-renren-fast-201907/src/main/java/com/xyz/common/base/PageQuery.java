/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.xyz.common.base;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xyz.common.constant.Constants;
import com.xyz.common.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 查询参数
 *
 * @author Mark sunlightcs@gmail.com
 */
public class PageQuery<T> {

    public IPage<T> getPage(Map<String, Object> params) {
        return this.getPage(params, null, false);
    }

    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        // 分页参数进行转换
        // page --> currentPage
        // limit --> pageSize
        long curPage = 1;
        long limit = 10;

        if (params.get(Constants.PAGE) != null) {
            // 可能会报 int -> String的错误
            // curPage = Long.parseLong((String)params.get(Constants.PAGE));
            curPage = Convert.toLong(params.get(Constants.PAGE));
        }

        if (params.get(Constants.LIMIT) != null) {
            limit = Convert.toLong(params.get(Constants.LIMIT));
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);

        //分页参数
        params.put(Constants.PAGE, page);

        // 排序字段
        // 防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = SQLFilter.sqlInject((String) params.get(Constants.ORDER_FIELD));
        String order = (String) params.get(Constants.ORDER);

        // 前端字段排序
        if (StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)) {
            if (Constants.ASC.equalsIgnoreCase(order)) {
                return page.addOrder(OrderItem.asc(defaultOrderField));
            } else {
                return page.addOrder(OrderItem.desc(defaultOrderField));
            }
        }

        //默认排序
        if (isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        } else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }
}
