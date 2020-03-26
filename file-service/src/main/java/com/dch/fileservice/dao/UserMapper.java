package com.dch.fileservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dch.fileservice.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
