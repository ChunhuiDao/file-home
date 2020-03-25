package com.dch.fileservice.controller;

import com.dch.fileservice.dao.UserDao;
import com.dch.fileservice.model.ApiResult;
import com.dch.fileservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TestController {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserDao userDao;

    @PostMapping("/add")
    public ApiResult add(@RequestBody @Valid User user, BindingResult result) {
        ApiResult apiResult = ApiResult.getInstance();
        if (result.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            result.getAllErrors().forEach(objectError -> {
                sb.append(objectError.getDefaultMessage());
                sb.append("； ");
            });
            return apiResult.setFailure().setMsg(sb.toString());
        }
        userDao.save(user);
        apiResult.setSuccess().setData(apiResult);
        return apiResult;
    }

    @GetMapping("/{id}")
    public ApiResult getUser(@PathVariable("id") Integer id) {
        ApiResult apiResult = ApiResult.getInstance();
        Object object = redisTemplate.opsForValue().get(id + "");
        User user;
        if (null != object) {
            user = (User) object;
        } else {
            user = userDao.getOne(id);
            if (null != user.getId()) {
                redisTemplate.opsForValue().set(user.getId() + "", user);
            }
        }
        apiResult.setSuccess().setData(user);
        return apiResult;
    }
}
