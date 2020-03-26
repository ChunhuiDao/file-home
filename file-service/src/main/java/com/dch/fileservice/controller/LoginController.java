package com.dch.fileservice.controller;

import com.dch.fileservice.config.SessionConfig;
import com.dch.fileservice.model.ApiResult;
import com.dch.fileservice.model.User;
import com.dch.fileservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.dch.common.utils.CommonUtils.getBindingResultMsg;
import static com.dch.common.utils.CommonUtils.isEmpty;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册接口
     *
     * @param user
     * @param result
     * @return
     */
    @PostMapping("/user/register")
    public ApiResult userRegister(@RequestBody @Valid User user, BindingResult result) {
        ApiResult apiResult = new ApiResult();
        if (!isEmpty(getBindingResultMsg(result))) {
            return apiResult.setFailure().setMsg(getBindingResultMsg(result));
        }
        return apiResult.setSuccess().setData(userService.register(user));
    }

    /**
     * 登录接口
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ApiResult login(@RequestBody @Valid User user, BindingResult result, HttpServletRequest httpServletRequest) {
        ApiResult apiResult = new ApiResult();
        if (!isEmpty(getBindingResultMsg(result))) {
            return apiResult.setFailure().setMsg(getBindingResultMsg(result));
        }
        user = userService.selectByNameAndPassword(user);
        if (!isEmpty(user)) {
            httpServletRequest.getSession().setAttribute(SessionConfig.USER_SESSION_KEY, user.getId());
            return apiResult.setSuccess().setData(user);
        }
        return apiResult.setFailure();
    }

    /**
     * 登录接口
     *
     * @param name
     * @param password
     * @return
     */
    @GetMapping("/login/{name}/{password}")
    public ApiResult login(@PathVariable("name") String name, @PathVariable("password") String password, HttpServletRequest httpServletRequest) {
        ApiResult apiResult = new ApiResult();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user = userService.selectByNameAndPassword(user);
        if (!isEmpty(user)) {
            httpServletRequest.getSession().setAttribute(SessionConfig.USER_SESSION_KEY, user.getId());
            return apiResult.setSuccess().setData(user);
        }
        return apiResult.setFailure();
    }

    @GetMapping("/test")
    public void test() {
        System.out.println("========成功=========");
    }

}
