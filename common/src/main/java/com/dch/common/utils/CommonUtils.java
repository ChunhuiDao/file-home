package com.dch.common.utils;

import org.springframework.validation.BindingResult;

public class CommonUtils {

    /**
     * 判空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        return null == object || "".equals(object);
    }

    /**
     * BindingResult错误信息提取
     *
     * @param result
     * @return
     */
    public static String getBindingResultMsg(BindingResult result) {
        if (result.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            result.getAllErrors().forEach(objectError -> {
                sb.append(objectError.getDefaultMessage());
                sb.append("；");
            });
            return sb.toString();
        }
        return null;
    }
}
