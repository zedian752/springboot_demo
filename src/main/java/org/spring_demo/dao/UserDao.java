package org.spring_demo.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.spring_demo.entity.User;

import java.util.List;


public interface UserDao extends BaseMapper<User> {
    List<User> findAll();
}
