package com.dch.fileservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 注册用户实体，可以增加额外条件
 */
@Setter
@Getter
public class UserRegister extends User {
    /**
     * 11位手机号码
     */
    @NotBlank(message = "phone不能为空！")
    private String phone;
}
