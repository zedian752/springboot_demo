package org.spring_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.spring_demo.dao.UserDaoPlus;
import org.spring_demo.entity.Student;
import org.spring_demo.entity.User;
import org.spring_demo.utils.JedisResPool;
import org.spring_demo.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@ControllerAdvice
public class emptyController {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    UserDaoPlus userDaoPlus;

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    JedisResPool JedisResPool;
//
//    @RequestMapping(value = "/quick")
//    publi    @Autowired
////    testService testService;c DataResponse<String> quick2(@RequestBody(required = false) Fortest test, @RequestHeader Map hedader) throws Exception {
//
//        return new DataResponse<>("23", 200) ;
//    }
//    @whiteLIst
    @GetMapping(value = "/quick", produces="application/json;charset=UTF-8")
    public String quick2(HttpServletResponse response, HttpServletRequest request) throws Exception {
        response.addHeader("content-type", "application/json;charset=utf-8");

        Jedis jedis = JedisResPool.getJedis();
        User user1 = new User();
        user1.setTelephone("15441247121");
        String res = RedisUtils.setObject(jedis, "1", user1);
        User user2 = RedisUtils.getObject(jedis, "1", User.class);

        user2 = null;
        user2.setTelephone("123");
//        response.setContentType("application/json;charset=utf-8");
        return "efsfs网盘fsfs";
    }
//    @RequestMapping(value = "/quickMap")
//    public Map quick2(@RequestBody(required = false) Fortest fortest) throws IOException {
//        Socket a = new Socket("1277", 32);
//
//
//        return new HashMap<String, String>(){{put("1","2");}};
//    }
    // 批量上传文件
    @PostMapping("/import")
    public void upload_test(Student student) { //
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        for (MultipartFile multipartFile : student.getFile()) {
            try {
                String path =  multipartFile.getOriginalFilename();
                multipartFile.transferTo(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            multipartFile.getOriginalFilename();
        }

        System.out.println(1);
    }
//    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionhandler(){
        return new ResponseEntity<String>("服务端异常500", HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
