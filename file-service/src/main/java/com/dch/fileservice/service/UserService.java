package com.dch.fileservice.service;

import com.dch.fileservice.model.User;

public interface UserService {
    /**
     * 用户注册
     * 若返回null说明用户已存在
     *
     * @param user
     * @return
     */
    User register(User user);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    User selectById(Integer id);

    /**
     * 根据name查询用户
     *
     * @param name
     * @return
     */
    User selectByName(String name);

    /**
     * 根据phone查询用户
     *
     * @param phone
     * @return
     */
    User selectByPhone(String phone);

    /**
     * 根据账户、密码查询用户
     *
     * @param user
     * @return
     */
    User selectByNameAndPassword(User user);
}
