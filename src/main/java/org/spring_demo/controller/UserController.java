package org.spring_demo.controller;

//import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.spring_demo.dao.UserDaoPlus;
import org.spring_demo.entity.Student;
import org.spring_demo.entity.User;
import org.spring_demo.service.dubbo.impl.UserApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserDaoPlus userDaoPlus;
    @Autowired
    UserApiImpl userApi;
    @PostMapping("/addUser")
//    public void addUser(@Validated @RequestBody User user, BindingResult br) {
    public List<User> addUser(@RequestBody(required = false) User user) {
        Student student = new Student();
        new UpdateWrapper<Student>(student);
        return userDaoPlus.getBaseMapper().selectList(new QueryWrapper<User>());
    }
    @PostMapping("/findUsers")
    public List<User> findAllUsers() {
        return userApi.findAllUsers();
    }
    // 跳转
    @GetMapping("/ggg")
    public void redi(HttpServletResponse response) throws Exception{
        response.sendRedirect("/some-url");
    }
    @PostMapping("/getPage")
    List<User> delUser(@RequestBody User user) {
        List list = Arrays.asList(1,2,3);
        return userDaoPlus.list(new QueryWrapper<User>().le("create_time", user.getCreateTime()).or().ge("create_time", user.getCreateTime()));
    }

    @GetMapping("/addUser9999")
    String addUser(String userId) throws InterruptedException {
//        return;
        String res = userApi.addUser(userId);
      return res;
    }
    @GetMapping("/testAsyncTask")
    void testAsyncTask(Integer args) throws ExecutionException, InterruptedException {
        userApi.asyncTest(args);
    }
}
