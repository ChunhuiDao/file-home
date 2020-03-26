package com.dch.fileservice.controller;

import com.dch.fileservice.dao.UserMapper;
import com.dch.fileservice.model.ApiResult;
import com.dch.fileservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

import static com.dch.common.utils.CommonUtils.getBindingResultMsg;
import static com.dch.common.utils.CommonUtils.isEmpty;

@RestController
public class TestController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/add")
    public ApiResult add(@RequestBody @Valid User user, BindingResult result) {
        ApiResult apiResult = new ApiResult();
        if (!isEmpty(getBindingResultMsg(result))) {
            return apiResult.setFailure().setMsg(getBindingResultMsg(result));
        }
        userMapper.insert(user);
        return apiResult.setSuccess().setData(user);
    }

    @GetMapping("/{id}")
    public ApiResult getUser(@PathVariable("id") Integer id) {
        ApiResult apiResult = new ApiResult();
        Object object = redisTemplate.opsForValue().get(id + "");
        User user;
        if (!isEmpty(object)) {
            user = (User) object;
        } else {
            user = userMapper.selectById(id);
            if (!isEmpty(user.getId())) {
                redisTemplate.opsForValue().set(user.getId() + "", user, 30, TimeUnit.SECONDS);
            }
        }
        return apiResult.setSuccess().setData(user);
    }
}
