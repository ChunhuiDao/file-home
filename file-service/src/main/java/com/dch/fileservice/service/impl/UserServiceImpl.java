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
     * 若返回null说明用户已存在
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public User register(User user) {
        // 根据name查找
        if (null != userMapper.selectOne(new QueryWrapper<User>().eq("name", user.getName()))) {
            return null;
        }
        // 根据phone查找
        if (null != userMapper.selectOne(new QueryWrapper<User>().eq("phone", user.getPhone()))) {
            return null;
        }
        userMapper.insert(user);
        user = userMapper.selectById(user.getId());
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
     * 根据name查询用户
     *
     * @param name
     * @return
     */
    @Override
    public User selectByName(String name) {
        return userMapper.selectOne(
                new QueryWrapper<User>()
                        .eq("name", name)
        );
    }

    /**
     * 根据phone查询用户
     *
     * @param phone
     * @return
     */
    @Override
    public User selectByPhone(String phone) {
        return userMapper.selectOne(
                new QueryWrapper<User>()
                        .eq("phone", phone)
        );
    }

    /**
     * 根据账户、密码查询用户
     *
     * @param user
     * @return
     */
    @Override
    public User selectByNameAndPassword(User user) {
        return userMapper.selectOne(
                new QueryWrapper<User>()
                        .eq("name", user.getName())
                        .eq("password", user.getPassword())
        );
    }
}
