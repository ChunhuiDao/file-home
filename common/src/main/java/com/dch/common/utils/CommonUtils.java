package com.dch.common.utils;

import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public final class CommonUtils {

    /**
     * 判空
     *
     * @param object
     * @return
     */
    public static boolean isBlank(Object object) {
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
        return "";
    }

    /**
     * 获取线程中HttpServletRequest对象
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

}
