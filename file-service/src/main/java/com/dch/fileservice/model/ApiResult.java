package com.dch.fileservice.model;

import lombok.Data;

@Data
public class ApiResult implements Cloneable {
    /**
     * 成功码
     */
    private static final String CODE_SUCCESS = "200";
    /**
     * 失败码
     */
    private static final String CODE_FAILURE = "201";
    /**
     * 消息
     */
    private String msg;
    /**
     * 状态码
     */
    private String code;
    /**
     * 数据
     */
    private Object data;

    private ApiResult() {
    }

    private static final ApiResult result = new ApiResult();

    public static ApiResult getInstance() {
        try {
            return (ApiResult) result.clone();
        } catch (CloneNotSupportedException e) {
            return new ApiResult();
        }
    }

    public ApiResult setSuccess() {
        this.code = CODE_SUCCESS;
        return this;
    }

    public ApiResult setFailure() {
        this.code = CODE_FAILURE;
        return this;
    }

    public ApiResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ApiResult setData(Object data) {
        this.data = data;
        return this;
    }

}
