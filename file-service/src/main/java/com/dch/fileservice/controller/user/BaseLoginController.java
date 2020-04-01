package com.dch.fileservice.controller.user;

import com.dch.fileservice.controller.BaseController;
import com.dch.fileservice.model.ApiResult;
import com.dch.fileservice.model.User;
import com.dch.fileservice.service.UserService;
import org.springframework.validation.BindingResult;

import static com.dch.common.utils.CommonUtils.getBindingResultMsg;
import static com.dch.common.utils.CommonUtils.isBlank;

public class BaseLoginController extends BaseController {
    private UserService userService;

    protected UserService getUserService() {
        return userService;
    }

    protected ApiResult userRegister(User user, BindingResult result) {
        ApiResult apiResult = new ApiResult();
        if (!isBlank(getBindingResultMsg(result))) {
            return apiResult.setFailure().setMsg(isBlank(user.getPhone()) ? getBindingResultMsg(result) + "phone不能为空！" : getBindingResultMsg(result));
        }
        user = getUserService().register(user);
        if (null == user) {
            return apiResult.setFailure().setMsg("该用户名或电话号码已注册！");
        }
        return apiResult.setSuccess().setData(user);
    }
}
