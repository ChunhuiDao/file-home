package com.dch.fileservice.interceptor;

import com.dch.fileservice.config.SessionConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import static com.dch.common.utils.CommonUtils.isBlank;
import static com.dch.common.utils.JwtUtils.decodeToken;

/**
 * 登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Value("${jwt.jwt-head}")
    private String jwtHead;
    @Value("${jwt.secret-key}")
    private String secretKey;

    /**
     * 登录验证，支持session和jwt
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
        // session验证
        if (!isBlank(request.getSession().getAttribute(SessionConfig.USER_SESSION_KEY))) {
            return true;
        }
        // token验证
        String jwtToken = request.getHeader(jwtHead);
        if (!isBlank(jwtToken)) {
            return null != decodeToken(jwtToken, secretKey) && decodeToken(jwtToken, secretKey).size() > 0;
        }
        returnExceptionToPage(response, "请登录！");
        return false;
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
    protected void returnExceptionToPage(HttpServletResponse response, String msg) throws Exception {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(msg);
        pw.flush();
        pw.close();
    }
}
