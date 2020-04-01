package com.dch.fileservice.controller;

import com.dch.fileservice.model.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/t1")
    public ApiResult t1() {
        return new ApiResult().setSuccess().setMsg("访问测试：成功！");
    }
}
