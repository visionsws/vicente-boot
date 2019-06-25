package com.vicente.vicenteboot.common.result;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.vicente.vicenteboot.common.page.PageUtil;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Result 工厂类
 */
public class ResultBuilder {

    // json 是否美化
    private static final boolean isPretty = Boolean.TRUE;

    private ResultBuilder() {
    }

    /**
     * 如果list是分页拦截器影响到的数据，那么paging就是正确的。
     * 如果list是其它方式产生的，比如CollectionUtil.convert(...)那么paging就不正确。 此时使用其它方法
     *
     * @param records
     * @param <T>
     * @return
     */
    public static <T> String success(List<T> records) {
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setValid(Boolean.TRUE);
        result.setResultCode(RespCodeConstant.SUCCESS);
        if (!CollectionUtils.isEmpty(records)) {
            result.setModels(records);
            result.setPaging(PageUtil.createInfo(records));
        }
        return JSON.toJSONString(result, isPretty);
    }

    /**
     * 如果list是其它方式产生的，比如CollectionUtil.convert(...)那么paging就是需要显式传递的。
     *
     * @param records
     * @param pageInfo
     * @param <T>
     * @return
     */
    public static <T> String success(List<T> records, PageInfo pageInfo) {
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setValid(Boolean.TRUE);
        result.setResultCode(RespCodeConstant.SUCCESS);
        if (!CollectionUtils.isEmpty(records)) {
            result.setModels(records);
            result.setPaging(pageInfo);
        }
        return JSON.toJSONString(result, isPretty);
    }

    public static <T> String success(T record) {
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setValid(Boolean.TRUE);
        result.setResultCode(RespCodeConstant.SUCCESS);
        result.setModel(record);
        return JSON.toJSONString(result, isPretty);
    }

    public static Result unsuccess(String msg) {
        Result result = new Result<>();
        result.setValid(Boolean.FALSE);
        result.setSuccess(Boolean.FALSE);
        result.setResultCode(RespCodeConstant.UNKNOWED_ERROR);
        result.setMessage(msg);
        return result;
    }

    public static String unvalid(String msg) {
        Result result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setValid(Boolean.FALSE);
        result.setResultCode(RespCodeConstant.PARAM_NOT_MATCH);
        result.setMessage(msg);
        return JSON.toJSONString(result, isPretty);
    }

}
