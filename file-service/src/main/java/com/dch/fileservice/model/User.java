package com.dch.fileservice.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
public class User implements Serializable {
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
     * 11位手机号码
     */
    private String phone;
    /**
     * 备注
     */
    private String note;
    /**
     * 账户类型，枚举id=1
     */
    private Integer type;
    /**
     * 账户状态：1有效，2无效
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 更新时间
     */
    private Date updated;
}
