package com.dch.fileservice.controller.user;

import com.dch.fileservice.model.ApiResult;
import com.dch.fileservice.model.User;
import com.dch.fileservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.dch.common.utils.CommonUtils.getBindingResultMsg;
import static com.dch.common.utils.CommonUtils.isBlank;

@RestController
@RequestMapping("/web/user")
public class WebLoginController extends BaseLoginController {
    @Autowired
    private UserService userService;

    @Override
    protected UserService getUserService() {
        return this.userService;
    }

    /**
     * 用户注册接口
     *
     * @param user
     * @param result
     * @return
     */
    @PostMapping("/register")
    public ApiResult register(@RequestBody @Valid User user, BindingResult result) {
        return userRegister(user, result);
    }

    /**
     * 登录接口
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ApiResult login(@RequestBody @Valid User user, BindingResult result) {
        ApiResult apiResult = new ApiResult();
        if (!isBlank(getBindingResultMsg(result))) {
            return apiResult.setFailure().setMsg(getBindingResultMsg(result));
        }
        if (null != getUserSession()) {
            return apiResult.setSuccess().setData(getUserSession()).setMsg("您已登录！请勿重复登录。");
        }
        user = userService.selectByNameAndPassword(user);
        if (!isBlank(user)) {
            setUserSession(user);
            return apiResult.setSuccess().setData(user).setMsg("登录成功！");
        }
        return apiResult.setFailure().setMsg("登录失败，请检查账户和密码！");
    }

}
