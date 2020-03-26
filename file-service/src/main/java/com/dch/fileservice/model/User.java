package com.dch.fileservice.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@TableName("user")
public class User {
    @TableId
    private Integer id;
    /**
     * 账户
     */
    @NotBlank(message = "name不能为空！")
    private String name;
    /**
     * 密码
     */
    @NotBlank(message = "password不能为空！")
    private String password;
    /**
     * 备注
     */
    private String note;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 更新时间
     */
    private Date updated;
}
