package org.spring_demo.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.spring_demo.entity.User;
import org.springframework.stereotype.Service;

@Service
//public interface UserDaoPlus extends  BaseMapper<User> {
//}
public class UserDaoPlus extends ServiceImpl<UserDao,User> {

}