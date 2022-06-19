package org.spring_demo.service.dubbo;

import org.spring_demo.dao.UserDaoPlus;
import org.spring_demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class test{
    @Autowired
    UserDaoPlus userDaoPlus;
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    public void sha_bei(User user) throws Exception {

        User user2  = new User();
        user2.setUsername("7");
        userDaoPlus.save(user2);
//        if ("51".equals(user.getUsername())) throw new Exception();
    }
}
