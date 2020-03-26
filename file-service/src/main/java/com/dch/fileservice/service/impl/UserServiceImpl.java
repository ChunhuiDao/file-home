package com.dch.fileservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dch.fileservice.dao.UserMapper;
import com.dch.fileservice.model.User;
import com.dch.fileservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public User register(User user) {
        userMapper.insert(user);
        return user;
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 根据账户、密码查询用户
     *
     * @param user
     * @return
     */
    @Override
    public User selectByNameAndPassword(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", user.getName());
        queryWrapper.eq("password", user.getPassword());
        return userMapper.selectOne(queryWrapper);
    }
}
