package com.dch.fileservice.controller;

import com.dch.fileservice.config.SessionConfig;
import com.dch.fileservice.model.User;

import static com.dch.common.utils.CommonUtils.getHttpServletRequest;

/**
 * 公共控制器
 */
public class BaseController {
    /**
     * 储存登录用户信息到session
     *
     * @param user
     */
    public static void setUserSession(User user) {
        getHttpServletRequest().getSession().setAttribute(SessionConfig.USER_SESSION_KEY, user);
    }

    /**
     * 从session获取用户信息
     */
    public static User getUserSession() {
        return (User) getHttpServletRequest().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
    }
}
