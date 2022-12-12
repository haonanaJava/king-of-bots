package com.kob.backend.common;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

public class Query<T> {

    public IPage<T> getPage(Map<String, Object> params) {
        return this.getPage(params, null, false);
    }

    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        if (params.get(Constants.PAGE) != null) {
            curPage = Long.parseLong((String) params.get(Constants.PAGE));
        }
        if (params.get(Constants.LIMIT) != null) {
            limit = Long.parseLong((String) params.get(Constants.LIMIT));
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);

        //没有排序字段，则不排序
        if (StrUtil.isBlank(defaultOrderField)) {
            return page;
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
