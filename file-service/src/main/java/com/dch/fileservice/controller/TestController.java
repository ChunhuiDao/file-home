package com.dch.fileservice.controller;

import com.dch.fileservice.model.ApiResult;
import com.dch.fileservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private UserService userService;

    @PostMapping("/t1")
    public ApiResult t1() {
        log.warn(Thread.currentThread().getName() + "::::::" + userService.selectById(new Random().nextInt(5) + 1).toString());
        return new ApiResult().setSuccess().setMsg("访问测试：成功！");
    }
}
