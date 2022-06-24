package org.spring_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring_demo.dao.UserDaoPlus;
import org.spring_demo.entity.User;
import org.spring_demo.service.dubbo.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TransactionController {

    @Autowired
    UserDaoPlus userDaoPlus;
    @Autowired
    test t;

    @GetMapping("/transaction/required/{userName}")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void PROPAGATION_REQUIRED(@PathVariable("userName") String userName) throws Exception {

//    test t = new test();
//        userDaoPlus.save(user);   
//        for (int i = 0; i< 2;i++) {
            User user  = new User();
            user.setUsername(userName + 1);
            userDaoPlus.save(user);
            try {
            t.sha_bei(user);

            } catch (Exception e) {
                log.info(e.getMessage());
            }
//        }
    }

//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    void sha_bei(User user) throws Exception {

        User user2  = new User();
        user2.setUsername("7");
        userDaoPlus.save(user);
        if ("51".equals(user.getUsername())) throw new Exception();
    }
}


