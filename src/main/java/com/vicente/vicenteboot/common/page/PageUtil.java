package com.vicente.vicenteboot.common.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 分页工具类
 * Created by cipher on 2017/9/19.
 */
public class PageUtil {

    /**
     * 缺省页大小
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    private static final Logger LOG = LoggerFactory.getLogger(PageUtil.class);

    private PageUtil() {
    }

    /**
     * 开始分页
     *
     * @param pagingQuery 分页参数
     */
    public static void startPage(PagingQuery pagingQuery) {
        if (pagingQuery == null) {
            return;
        }
        PageHelper.startPage(pagingQuery.getPageIndex(), pagingQuery.getPageSize());
    }

    /**
     * 创建分页信息
     *
     * @param list 分页后的集合
     * @return 分页信息
     */
    public static PageInfo createInfo(List list) {
        PageInfo pageInfo = null;
        if (!CollectionUtils.isEmpty(list)) {
            pageInfo = new PageInfo(list);
            Class<?> clazz = pageInfo.getClass();
            try {
                // 去掉 list 属性
                Field listField = clazz.getDeclaredField("list");
                listField.setAccessible(true);
                listField.set(pageInfo, null);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                LOG.error("创建分页信息失败", e);
            }
        }
        return pageInfo;
    }

}
