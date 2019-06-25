package com.vicente.vicenteboot.common.result;


import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code Result<T>} is the root of the standard result hierarchy.
 *
 * @author Patrick.he
 * @version 1.0
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public Result() {
        this.success = true;
        this.isValid = true;
    }

    /**
     * the flag of transaction or operation
     */
    private boolean success;
    /**
     * the returning code of transaction or operation
     */
    private String resultCode;
    /**
     * the returning message of transaction or operation
     */
    private String message;
    /**
     * single model
     */
    private T model;
    /**
     * multi models
     */
    private List<T> models;
    /**
     * paging info
     */
    private PageInfo<T> paging;

    /**
     * extra info
     */
    private Object extra;

    /**
     * is valid
     */
    private boolean isValid;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public List<T> getModels() {
        if (null != this.models) {
            return models;
        } else {
            return new ArrayList<T>();
        }
    }

    public void setModels(List<T> models) {
        this.models = models;
    }

    public PageInfo<T> getPaging() {
        return paging;
    }

    public void setPaging(PageInfo<T> paging) {
        this.paging = paging;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
