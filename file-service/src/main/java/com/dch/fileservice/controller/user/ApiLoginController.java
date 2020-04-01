package com.dch.fileservice.controller.user;

import com.dch.fileservice.model.ApiResult;
import com.dch.fileservice.model.User;
import com.dch.fileservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dch.common.utils.CommonUtils.getBindingResultMsg;
import static com.dch.common.utils.CommonUtils.isBlank;
import static com.dch.common.utils.JwtUtils.createToken;

@RestController
@RequestMapping("/api/user")
public class ApiLoginController extends BaseLoginController {
    @Value("${jwt.expire}")
    private Integer expire;
    @Value("${jwt.secret-key}")
    private String secretKey;

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
        user = userService.selectByNameAndPassword(user);
        if (!isBlank(user)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("name", user.getName());
            claims.put("type", user.getType());
            return apiResult.setSuccess().setMsg("登录成功！").setData(createToken(claims, expire, TimeUnit.HOURS, secretKey));
        }
        return apiResult.setFailure().setMsg("登录失败，请检查账户和密码！");
    }

}
