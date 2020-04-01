package com.dch.fileservice.interceptor;

import com.dch.fileservice.config.SessionConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.dch.common.utils.CommonUtils.isBlank;

/**
 * 登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 处理业务前执行
     *
     * @param request
     * @param response
     * @param object
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object)
            throws Exception {
        boolean flag = false;
        //判断是否登录
        if (!isBlank(request.getSession().getAttribute(SessionConfig.USER_SESSION_KEY))) {
            flag = true;
        } else {
            returnExceptionToPage(response, "请登录！");
        }
        return flag;
    }

    /**
     * 渲染视图前执行
     *
     * @param request
     * @param response
     * @param object
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求完成后执行
     *
     * @param request
     * @param response
     * @param object
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e) throws Exception {

    }

    /**
     * 写入response返回浏览器
     *
     * @param response
     * @param msg
     * @throws Exception
     */
    private void returnExceptionToPage(HttpServletResponse response, String msg) throws Exception {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(msg);
        pw.flush();
        pw.close();
    }
}
