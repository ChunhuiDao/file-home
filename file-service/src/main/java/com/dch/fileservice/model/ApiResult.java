package com.dch.fileservice.model;

import lombok.Data;

@Data
public class ApiResult {
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
