package com.dch.fileservice.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.dch.common.utils.CommonUtils.isEmpty;

/**
 * 登陆拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 处理业务前执行
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param object
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object)
            throws Exception {
        boolean flag = false;
        //判断是否登录
        if (!isEmpty(httpServletRequest.getSession().getAttribute(SessionConfig.USER_SESSION_KEY))) {
            flag = true;
        } else {
            returnExceptionToPage(httpServletResponse, "请登录！");
        }
        return flag;
    }

    /**
     * 渲染视图前执行
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求完成后执行
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                                Exception e) throws Exception {

    }

    private void returnExceptionToPage(HttpServletResponse httpServletResponse, String msg) throws Exception {
        httpServletResponse.reset();
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter pw = httpServletResponse.getWriter();
        pw.write(msg);
        pw.flush();
        pw.close();
    }
}
